package damian.kcal.dzienniczek.controller;

import damian.kcal.dzienniczek.model.Diary;
import damian.kcal.dzienniczek.model.Product;
import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.model.Weight;
import damian.kcal.dzienniczek.service.DiaryService;
import damian.kcal.dzienniczek.service.ProductService;
import damian.kcal.dzienniczek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.Console;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class DiaryController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DiaryService diaryService;

    @RequestMapping(value= {"/user/diary"}, method= RequestMethod.GET)
    public ModelAndView diary() throws ParseException {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());



        Product product = new Product();

        List<Product> productList = new ArrayList<Product>();
        productList = productService.findAllProducts();


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        System.out.println("Report Date: " + reportDate);

        Diary diary = new Diary();
        List<Diary> diaryList = new ArrayList<Diary>();
        //diaryList = diaryService.findByUser(user);
        diaryList = diaryService.findByUserAndDate(user, new SimpleDateFormat("yyyy-MM-dd").parse(reportDate));





        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("allProductList", productList);
        model.addObject("allDiaryList", diaryList);
        model.setViewName("user/diary");
        return model;
    }

    @RequestMapping(value= {"/user/diary"}, method= RequestMethod.POST)
    public ModelAndView createDiary(@Valid Diary diary) throws ParseException {
        ModelAndView model = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists != null) {

            Date date = new Date();


            diary.setUser(user);
            diary.setDate(date);
            diaryService.saveDiary(diary);

            Product product = new Product();
            List<Product> productList = new ArrayList<Product>();
            productList = productService.findAllProducts();


            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);
            System.out.println("Report Date: " + reportDate);


            //Diary diary2 = new Diary();
            List<Diary> diaryList = new ArrayList<Diary>();
            //diaryList = diaryService.findByUser(user);
            diaryList = diaryService.findByUserAndDate(user, new SimpleDateFormat("yyyy-MM-dd").parse(reportDate));

            model.addObject("userName", user.getFirstname() + " " + user.getLastname());
            model.addObject("allProductList", productList);
            model.addObject("diary", diary);
            model.addObject("allDiaryList", diaryList);
            model.setViewName("user/diary");
        }
        return model;
    }




    @RequestMapping(value= {"/user/diarySum"}, method=RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object[][] getDiarySum() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());


        Object[][] maxObd =  diaryService.findDiarySum();
        System.out.println(maxObd[0][1]);

        return diaryService.findDiarySum();


    }



    @RequestMapping(value= {"/user/diary/{diaryId}"}, method=RequestMethod.DELETE)
    @ResponseBody
    public String deleteWeight(HttpServletResponse response, @PathVariable int diaryId) {

        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // User user = userService.findUserByEmail(auth.getName());

        //User userExists = userService.findUserByEmail(user.getEmail());

        diaryService.deleteById(diaryId);
        System.out.println("delete method");

        //if(userExists != null) {

        //weight.setWeight(80);
        //weight.setUser(user);

        //weightService.deleteById(weightId);
        //System.out.println("delete method");

        //List<Weight> weightList = new ArrayList<Weight>();
        //weightList = weightService.findByUser(user);

        //model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        //model.addObject("userWeights", weightList);
        //model.addObject("weight", weight);
        //model.setViewName("user/weight");
        //}
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        return "TEXT";
    }


}
