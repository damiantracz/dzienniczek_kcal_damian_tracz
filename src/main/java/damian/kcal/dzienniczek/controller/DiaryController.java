package damian.kcal.dzienniczek.controller;

import damian.kcal.dzienniczek.model.Diary;
import damian.kcal.dzienniczek.model.Product;
import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.service.DiaryService;
import damian.kcal.dzienniczek.service.ProductService;
import damian.kcal.dzienniczek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DiaryController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DiaryService diaryService;

    @RequestMapping(value= {"/user/diary"}, method= RequestMethod.GET)
    public ModelAndView diary() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());


        Product product = new Product();

        List<Product> productList = new ArrayList<Product>();
        productList = productService.findAllProducts();

        Diary diary = new Diary();
        List<Diary> diaryList = new ArrayList<Diary>();
        diaryList = diaryService.findByUser(user);

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("allProductList", productList);
        model.addObject("allDiaryList", diaryList);
        model.setViewName("user/diary");
        return model;
    }

    @RequestMapping(value= {"/user/diary"}, method= RequestMethod.POST)
    public ModelAndView createDiary(@Valid Diary diary) {
        ModelAndView model = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists != null) {

            diary.setUser(user);
            diaryService.saveDiary(diary);

            Product product = new Product();
            List<Product> productList = new ArrayList<Product>();
            productList = productService.findAllProducts();

            model.addObject("userName", user.getFirstname() + " " + user.getLastname());
            model.addObject("allProductList", productList);
            model.addObject("diary", diary);
            model.setViewName("user/diary");
        }
        return model;
    }

}
