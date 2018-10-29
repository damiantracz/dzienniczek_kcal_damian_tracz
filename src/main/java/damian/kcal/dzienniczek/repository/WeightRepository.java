package damian.kcal.dzienniczek.repository;

import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.model.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("weightRepository")
public interface WeightRepository extends JpaRepository<Weight, Integer> {

    List<Weight> findByUser(User user);
}