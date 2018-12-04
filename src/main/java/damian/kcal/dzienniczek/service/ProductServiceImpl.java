package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.Product;
import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product){
        productRepository.save(product);
    }

    @Override
    public List<Product> findByUser(User user) {return (List<Product>) productRepository.findByUser(user); }

}
