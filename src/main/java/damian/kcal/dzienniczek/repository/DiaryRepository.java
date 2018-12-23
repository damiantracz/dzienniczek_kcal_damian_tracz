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

    //

    //@Query(value ="SELECT id, weight FROM Diary WHERE id = 115", nativeQuery = true)
    @Query(value ="SELECT ROUND(SUM(p.carbohydrates*(d.weight/100))) as carbohydratesDay, ROUND(SUM(p.protein*(d.weight/100))) as proteinDay, ROUND(SUM(p.fat*(d.weight/100))) as fatDay FROM `diary` d INNER JOIN `product` p ON d.product_id = p.id WHERE d.date = CURDATE()", nativeQuery = true)
    Object [][] findDiarySum();


}
