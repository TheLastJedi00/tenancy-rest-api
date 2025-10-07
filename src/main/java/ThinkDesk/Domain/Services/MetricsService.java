package ThinkDesk.Domain.Services;

import ThinkDesk.Domain.Models.Enums.TicketStatus;
import ThinkDesk.Domain.Models.Technician;
import ThinkDesk.Domain.Models.Ticket;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetricsService {

    private final TicketService ticketService;
    private final TechnicianService technicianService;

    public MetricsService(TicketService ticketService, TechnicianService technicianService) {
        this.ticketService = ticketService;
        this.technicianService = technicianService;
    }

    public Map<String, Object> getTeamMetrics(Long teamId) {
        List<Technician> teamMembers = technicianService.getByTeamId(teamId);
        List<Long> memberIds = teamMembers.stream().map(Technician::getId).toList();

        List<Ticket> resolvedTickets = ticketService.getResolvedTicketByTeam(memberIds, TicketStatus.CLOSED);
        List<Ticket> openTickets = ticketService.getOpenTicketsByTeam(memberIds, TicketStatus.CLOSED);

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
        List<Ticket> resolvedTickets = ticketService.getResolvedTicketByEmployee(employeeId, TicketStatus.CLOSED);
        List<Ticket> openTickets = ticketService.getOpenTicketsByEmployee(employeeId, TicketStatus.CLOSED);

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
