package damian.kcal.dzienniczek.repository;


import damian.kcal.dzienniczek.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByRoleName (String roleName);
}
