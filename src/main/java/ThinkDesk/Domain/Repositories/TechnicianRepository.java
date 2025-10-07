package ThinkDesk.Domain.Repositories;

import ThinkDesk.Domain.Models.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {
    List<Technician> findByTeamId(Long teamId);
    UserDetails findByEmail(String email);
}
