package damian.kcal.dzienniczek.repository;

import damian.kcal.dzienniczek.model.Makro;
import damian.kcal.dzienniczek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("makroRepository")
public interface MakroRepository extends JpaRepository<Makro, Integer> {

    List<Makro> findByUser(User user);

    void deleteById(Integer id);

}
