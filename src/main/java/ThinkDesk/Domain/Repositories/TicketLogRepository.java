package ThinkDesk.Domain.Repositories;

import ThinkDesk.Domain.Models.TicketLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketLogRepository extends JpaRepository<TicketLog, Long> {
}
