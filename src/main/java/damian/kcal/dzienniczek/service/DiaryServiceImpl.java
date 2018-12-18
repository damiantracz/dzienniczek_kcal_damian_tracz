package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.Diary;
import damian.kcal.dzienniczek.model.Makro;
import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.repository.DiaryRepository;
import damian.kcal.dzienniczek.repository.MakroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("diaryService")
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Override
    public void saveDiary(Diary diary) {diaryRepository.save(diary);}

    @Override
    public List<Diary> findByUser(User user) {return (List<Diary>) diaryRepository.findByUser(user);}

}
