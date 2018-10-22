package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.User;

import java.util.List;

public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);

    List<User> findAllUsers();
}

