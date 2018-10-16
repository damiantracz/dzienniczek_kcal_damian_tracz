package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByUserEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail);
    }

    @Override
    public void saveUser(User user){
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        //user.setUserPassword("test");
        //Role userRole = roleRepository.findByRoleName("ADMIN");
        //user.setRoleName(userRole);
        userRepository.save(user);
    }

}
