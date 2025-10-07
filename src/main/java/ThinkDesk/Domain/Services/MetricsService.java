package ThinkDesk.Domain.Services;

import ThinkDesk.Domain.Models.Admin;
import ThinkDesk.Domain.Models.Enums.TicketStatus;
import ThinkDesk.Domain.Models.Ticket;
import ThinkDesk.Domain.Repositories.AdminRepository;
import ThinkDesk.Domain.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetricsService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AdminRepository adminRepository;

    public Map<String, Object> getTeamMetrics(Long teamId) {
        List<Admin> teamMembers = adminRepository.findByTeamId(teamId);
        List<Long> memberIds = teamMembers.stream().map(Admin::getId).toList();

        List<Ticket> resolvedTickets = ticketRepository.findByTechnicianIdInAndStatus(memberIds, TicketStatus.CLOSED);
        List<Ticket> openTickets = ticketRepository.findByTechnicianIdInAndStatusNot(memberIds, TicketStatus.CLOSED);

        long slaMetCount = resolvedTickets.stream()
                .filter(ticket -> ticket.getClosedAt().isBefore(ticket.getResolutionDueDate()))
                .count();

        Map<String, Object> metrics = new HashMap<>();
        metrics.put("resolvedTickets", resolvedTickets.size());
        metrics.put("slaMet", slaMetCount);
        metrics.put("openTickets", openTickets.size());

        return metrics;
    }

    public Map<String, Object> getEmployeeMetrics(Long employeeId) {
        List<Ticket> resolvedTickets = ticketRepository.findByTechnicianIdAndStatus(employeeId, TicketStatus.CLOSED);
        List<Ticket> openTickets = ticketRepository.findByTechnicianIdAndStatusNot(employeeId, TicketStatus.CLOSED);

        long slaMetCount = resolvedTickets.stream()
                .filter(ticket -> ticket.getClosedAt().isBefore(ticket.getResolutionDueDate()))
                .count();

        Map<String, Object> metrics = new HashMap<>();
        metrics.put("resolvedTickets", resolvedTickets.size());
        metrics.put("slaMet", slaMetCount);
        metrics.put("openTickets", openTickets.size());

        return metrics;
    }
}
