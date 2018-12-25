package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.Product;
import damian.kcal.dzienniczek.model.User;

import java.util.List;

public interface ProductService {

    public void saveProduct(Product product);

    List<Product> findByUser(User user);

    List<Product> findAllProducts();

    void deleteById(Integer id);
}
