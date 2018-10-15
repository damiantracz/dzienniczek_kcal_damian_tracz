package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.User;

public interface UserService {

    public User findUserByName(String userName);

    public void saveUser(User user);
}

