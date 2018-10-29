package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.model.Weight;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeightService {

    //public Weight findWeightByUserId(Integer id);

    public void saveWeight(Weight weight);

    List<Weight> findAllWeights();


    List<Weight> findByUser(User user);
}

