package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.Makro;
import damian.kcal.dzienniczek.model.User;

import java.util.List;

public interface MakroService {

    public void saveMakro(Makro makro);

    List<Makro> findByUser(User user);
}
