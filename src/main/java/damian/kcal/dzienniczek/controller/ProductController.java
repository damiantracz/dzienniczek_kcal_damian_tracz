package damian.kcal.dzienniczek.controller;


import damian.kcal.dzienniczek.model.Product;
import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.model.Weight;
import damian.kcal.dzienniczek.service.ProductService;
import damian.kcal.dzienniczek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

        Product product = new Product();

        List<Product> productList = new ArrayList<Product>();
        productList = productService.findByUser(user);


        model.addObject("userName", user.getFirstname() + " " + user.getLastname());
        model.addObject("userProducts", productList);
        model.addObject("product", product);
        model.setViewName("user/product");
        return model;
    }


    @RequestMapping(value= {"/user/product"}, method=RequestMethod.POST)
    public ModelAndView createProduct(@Valid Product product, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {

           // product.setCarbohydrates(100);
            //product.setProtein(100);
            //product.setFat(100);
            product.setUser(user);
            productService.saveProduct(product);

            List<Product> productList = new ArrayList<Product>();
            productList = productService.findByUser(user);

            model.addObject("userName", user.getFirstname() + " " + user.getLastname());
            model.addObject("userProducts", productList);
            model.addObject("product", new Product());
            model.setViewName("user/product");
        }
        return model;
    }

}
