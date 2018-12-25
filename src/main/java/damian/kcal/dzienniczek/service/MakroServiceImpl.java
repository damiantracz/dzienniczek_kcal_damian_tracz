package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.Makro;
import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.repository.MakroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("makroService")
public class MakroServiceImpl implements MakroService{

    @Autowired
    private MakroRepository makroRepository;

    @Override
    public void saveMakro(Makro makro){
        makroRepository.save(makro);
    }

    @Override
    public List<Makro> findByUser(User user){
        return (List<Makro>) makroRepository.findByUser(user);
    }

    @Override
    public void deleteById(Integer id){makroRepository.deleteById(id);}

}
