package damian.kcal.dzienniczek.repository;


import damian.kcal.dzienniczek.model.Diary;
import damian.kcal.dzienniczek.model.Product;
import damian.kcal.dzienniczek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("diaryRepository")
public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    List<Diary> findByUser(User user);
}
