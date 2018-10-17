package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.User;

public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);
}

