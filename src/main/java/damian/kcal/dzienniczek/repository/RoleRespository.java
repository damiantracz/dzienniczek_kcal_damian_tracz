package damian.kcal.dzienniczek.repository;

import damian.kcal.dzienniczek.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("RoleRepository")
public interface RoleRespository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}

