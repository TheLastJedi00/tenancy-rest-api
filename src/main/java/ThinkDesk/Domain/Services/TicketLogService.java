package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.TicketLogDTO;
import ThinkDesk.Domain.Models.Ticket;
import ThinkDesk.Domain.Models.TicketLog;
import ThinkDesk.Domain.Repositories.TicketLogRepository;
import ThinkDesk.Infra.Mapper.TicketLogMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketLogService {
    private final TicketLogRepository ticketLogRepository;
    private final TicketLogMapper ticketLogMapper;
    private final TicketService ticketService;


    public TicketLogService(TicketLogRepository ticketLogRepository, TicketLogMapper ticketLogMapper, TicketService ticketService) {
        this.ticketLogRepository = ticketLogRepository;
        this.ticketLogMapper = ticketLogMapper;
        this.ticketService = ticketService;
    }

    public TicketLog create(TicketLogDTO ticketLogDTO){
        Long idTicket = ticketLogDTO.ticket_id();
        Ticket ticket = ticketService.getById(idTicket);
        TicketLog ticketLog = new TicketLog(ticketLogDTO, ticket);
        return ticketLogRepository.save(ticketLog);
    }

    public List<TicketLog> getAll() {
        return ticketLogRepository.findAll();
    }

    public TicketLog getById(Long id) {
        return ticketLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant com " + id + " não encontrado."));
    }

    public TicketLog update(Long id, TicketLogDTO ticketLogDTO) {
        TicketLog ticketLog = getById(id);
        ticketLogMapper.updateEntityFromDto(ticketLogDTO, ticketLog);
        return ticketLogRepository.save(ticketLog);
    }

    public void delete(Long id) {
        ticketLogRepository.deleteById(id);
    }
}
