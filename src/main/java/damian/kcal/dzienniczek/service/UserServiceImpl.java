package damian.kcal.dzienniczek.service;

import damian.kcal.dzienniczek.model.Role;
import damian.kcal.dzienniczek.model.User;
import damian.kcal.dzienniczek.repository.RoleRepository;
import damian.kcal.dzienniczek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByName(String userName){
        return userRepository.findByUserName(userName);
    }

    @Override
    public void saveUser(User user){
        //user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        user.setUserPassword("test");
        //Role userRole = roleRepository.findByRoleName("ADMIN");
        //user.setRoleName(userRole);
        userRepository.save(user);
    }

}
