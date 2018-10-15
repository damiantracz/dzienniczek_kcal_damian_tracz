package damian.kcal.dzienniczek.repository;

import damian.kcal.dzienniczek.model.Role;
import damian.kcal.dzienniczek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//public interface UserRepository extends CrudRepository<User, Long> {

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserName(String userName);
}
