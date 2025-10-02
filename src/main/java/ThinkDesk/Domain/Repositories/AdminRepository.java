package ThinkDesk.Domain.Repositories;

import ThinkDesk.Domain.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    UserDetails findByLogin(String login);
}
