package damian.kcal.dzienniczek.controller;


import damian.kcal.dzienniczek.model.Product;
import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.service.ProductService;
import damian.kcal.dzienniczek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value= {"/user/product"}, method= RequestMethod.GET)
    public ModelAndView weight() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        List<Product> productList = new ArrayList<Product>();
        productList = productService.findByUser(user);


        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("userProducts", productList);
        model.setViewName("user/product");
        return model;
    }

}
