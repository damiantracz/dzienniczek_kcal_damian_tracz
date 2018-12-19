package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.Diary;
import damian.kcal.dzienniczek.model.User;

import java.util.Date;
import java.util.List;

public interface DiaryService {
    public void saveDiary(Diary diary);

    List<Diary> findByUser(User user);

    List<Diary> findByUserAndDate(User user, Date date);
}
