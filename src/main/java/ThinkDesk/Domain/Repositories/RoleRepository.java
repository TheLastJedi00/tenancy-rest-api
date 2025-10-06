package ThinkDesk.Domain.Repositories;

import ThinkDesk.Domain.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByRole(String role);
}
