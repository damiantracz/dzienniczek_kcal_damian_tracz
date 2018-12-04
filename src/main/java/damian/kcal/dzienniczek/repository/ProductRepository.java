package damian.kcal.dzienniczek.repository;

import damian.kcal.dzienniczek.model.Product;
import damian.kcal.dzienniczek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByUser(User user);
}
