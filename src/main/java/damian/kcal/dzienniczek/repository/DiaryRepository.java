package damian.kcal.dzienniczek.repository;


import damian.kcal.dzienniczek.model.Diary;
import damian.kcal.dzienniczek.model.Product;
import damian.kcal.dzienniczek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("diaryRepository")
public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    List<Diary> findByUser(User user);

    //@Query
    //List<Diary> findByUser2(User user);

    //SELECT d.product_id FROM Diary d WHERE d.date BETWEEN '2018-12-18 00:00:00' AND '2018-12-18 23:59:59'
    //@Query("SELECT * FROM Diary d WHERE ")
    //SELECT d.product_id FROM Diary d WHERE d.date LIKE '2018-12-18%'
    List<Diary> findByUserAndDate(User user, Date date);


}
