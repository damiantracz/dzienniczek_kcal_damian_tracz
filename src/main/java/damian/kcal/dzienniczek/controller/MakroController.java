package damian.kcal.dzienniczek.controller;

import damian.kcal.dzienniczek.model.Makro;
import damian.kcal.dzienniczek.model.Product;
import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.model.Weight;
import damian.kcal.dzienniczek.service.MakroService;
import damian.kcal.dzienniczek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MakroController {

    @Autowired
    private MakroService makroService;

    @Autowired
    private UserService userService;


    @RequestMapping(value= {"/user/makro"}, method=RequestMethod.GET)
    public ModelAndView getMakro() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        List<Makro> makroList = new ArrayList<Makro>();
        makroList = makroService.findByUser(user);

        Makro makro = new Makro();

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("userMakros", makroList);
        model.addObject("makro", makro);
        model.setViewName("user/makro");
        return model;
    }


    @RequestMapping(value= {"/user/makro"}, method=RequestMethod.POST)
    public ModelAndView createMakro(@Valid Makro makro, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {

            // product.setCarbohydrates(100);
            //product.setProtein(100);
            //product.setFat(100);
            makro.setUser(user);
            makroService.saveMakro(makro);
        }

        List<Makro> makroList = new ArrayList<Makro>();
        makroList = makroService.findByUser(user);

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("userMakros", makroList);
        model.addObject("makro", makro);
        model.setViewName("user/makro");
        return model;
    }



    @RequestMapping(value= {"/user/makro/{makroId}"}, method=RequestMethod.DELETE)
    @ResponseBody
    public String deleteMakro(HttpServletResponse response, @PathVariable int makroId) {

        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // User user = userService.findUserByEmail(auth.getName());

        //User userExists = userService.findUserByEmail(user.getEmail());

        makroService.deleteById(makroId);
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






    @RequestMapping(value = {"/user/makro2"}, method= RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Makro> saveMakro(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Makro makro = new Makro();
        makro.setCarbohydrates(100);
        makro.setProtein(80);
        makro.setFat(50);
        makro.setUser(user);
        makroService.saveMakro(makro);

        return makroService.findByUser(user);
    }

}
