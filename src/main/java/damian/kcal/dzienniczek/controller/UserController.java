package damian.kcal.dzienniczek.controller;

import damian.kcal.dzienniczek.model.Makro;
import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.model.Weight;
import damian.kcal.dzienniczek.service.DiaryService;
import damian.kcal.dzienniczek.service.MakroService;
import damian.kcal.dzienniczek.service.UserService;
import damian.kcal.dzienniczek.service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MakroService makroService;

    @Autowired
    private WeightService weightService;

    @Autowired
    private DiaryService diaryService;

    @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();

        model.setViewName("user/login");
        return model;
    }

    @RequestMapping(value= {"/home2"}, method=RequestMethod.GET)
    public ModelAndView home2() {
        ModelAndView model = new ModelAndView();

        model.setViewName("home/home2");
        return model;
    }


    @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        Weight weight = new Weight();
        Makro makro = new Makro();
        model.addObject("user", user);
        model.addObject("weight", weight);
        model.addObject("makro", makro);
        model.setViewName("user/signup");

        return model;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, @Valid Weight weight, @Valid Makro makro, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("user/signup");
        } else {
            userService.saveUser(user);

            weight.setUser(user);
            weightService.saveWeight(weight);

            makro.setUser(user);
            makroService.saveMakro(makro);
            model.addObject("msg", "User has been registered successfully! Now you can login <a href='/login'></a>");
            model.addObject("user", new User());
            model.setViewName("user/signup");
        }

        return model;
    }

    @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        List<Makro> makroList = new ArrayList<Makro>();
        makroList = makroService.findByUser(user);

        Makro makro = new Makro();
        makro = makroList.get(makroList.size() - 1);

        List<Weight> weightList = new ArrayList<Weight>();
        weightList = weightService.findByUser(user);

        List<Float> onlyWeightList = new ArrayList<Float>();

        for (Weight weightL : weightList){
            onlyWeightList.add(weightL.getWeight());
            System.out.println(weightL.getWeight());
        }


        Object[][] sumMakro =  diaryService.findDiarySum();
        //System.out.println(sumMakro[0][1]);

        //SELECT d.id, p.carbohydrates, p.protein, p.fat FROM `diary` d INNER JOIN `product` p ON d.product_id = p.id WHERE d.date = CURDATE()

        //SELECT ROUND(SUM(p.carbohydrates*(d.weight/100))) as carbohydratesDay, ROUND(SUM(p.protein*(d.weight/100))) as proteinDay, ROUND(SUM(p.fat*(d.weight/100))) as fatDay FROM `diary` d INNER JOIN `product` p ON d.product_id = p.id WHERE d.date = CURDATE()

        //jeśli użytkownik nic jeszcze nie zjadł dzisiaj to zwraca null. Zamieniam null na 0.
        if (sumMakro[0][0] == null) { sumMakro[0][0] = 0.0; }
        if (sumMakro[0][1] == null) { sumMakro[0][1] = 0.0; }
        if (sumMakro[0][2] == null) { sumMakro[0][2] = 0.0; }
        //System.out.println(sumMakro[0][1]);

        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("userCarbohydrates", (makro.getCarbohydrates() - (Double) sumMakro[0][0]));
        model.addObject("userProtein", (makro.getProtein() - (Double) sumMakro[0][1]));
        model.addObject("userFat", (makro.getFat() - (Double) sumMakro[0][2]));
        model.addObject("onlyWeightList", onlyWeightList);
        model.addObject("weightList", weightList);
        model.setViewName("home/home");
        return model;
    }

    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public List<User> getAllUsers() {return userService.findAllUsers();}
}
