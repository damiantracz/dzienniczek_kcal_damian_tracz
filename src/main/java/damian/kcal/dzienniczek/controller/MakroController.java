package damian.kcal.dzienniczek.controller;

import damian.kcal.dzienniczek.model.Makro;
import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.model.Weight;
import damian.kcal.dzienniczek.service.MakroService;
import damian.kcal.dzienniczek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MakroController {

    @Autowired
    private MakroService makroService;

    @Autowired
    private UserService userService;


    @RequestMapping(value= {"/user/makro"}, method=RequestMethod.GET)
    public ModelAndView weight() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        List<Makro> makroList = new ArrayList<Makro>();
        makroList = makroService.findByUser(user);

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("userMakros", makroList);
        model.setViewName("user/makro");
        return model;
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
