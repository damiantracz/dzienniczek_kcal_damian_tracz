package damian.kcal.dzienniczek.repository;

import damian.kcal.dzienniczek.model.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("weightRepository")
public interface WeightRepository extends JpaRepository<Weight, Integer> {
    //Weight findWeightByUserId(Integer id);
}