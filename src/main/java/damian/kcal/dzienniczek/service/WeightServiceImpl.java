package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.Role;
import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.model.Weight;
import damian.kcal.dzienniczek.repository.RoleRespository;
import damian.kcal.dzienniczek.repository.UserRepository;
import damian.kcal.dzienniczek.repository.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("weightService")
public class WeightServiceImpl implements WeightService {

    @Autowired
    private WeightRepository weightRepository;



//    @Override
//    public Weight findWeightByUserId(Integer id) {
//        return weightRepository.findWeightByUserId(id);
//    }

    @Override
    public void saveWeight(Weight weight) {
        weightRepository.save(weight);
    }

}