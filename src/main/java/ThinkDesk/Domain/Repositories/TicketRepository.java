package ThinkDesk.Domain.Repositories;

import ThinkDesk.Domain.Models.Enums.TicketStatus;
import ThinkDesk.Domain.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByTechnicianIdInAndStatus(List<Long> technicianIds, TicketStatus status);

    List<Ticket> findByTechnicianIdInAndStatusNot(List<Long> technicianIds, TicketStatus status);

    List<Ticket> findByTechnicianIdAndStatus(Long technicianId, TicketStatus status);

    List<Ticket> findByTechnicianIdAndStatusNot(Long technicianId, TicketStatus status);
}
