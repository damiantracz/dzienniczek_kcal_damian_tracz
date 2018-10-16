package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.User;

public interface UserService {

    User findByUserEmail(String userEmail);

    void saveUser(User user);
}

