package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.PriorityDto;
import ThinkDesk.Domain.Models.Priority;
import ThinkDesk.Domain.Models.Tenant;
import ThinkDesk.Domain.Models.Ticket;
import ThinkDesk.Domain.Repositories.PriorityRepository;
import ThinkDesk.Infra.Mapper.PriorityMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class PriorityService {
    private final PriorityRepository priorityRepository;
    private final TicketService ticketService;
    private final TenantService tenantService;
    private final PriorityMapper priorityMapper;

    public PriorityService(PriorityRepository priorityRepository, TicketService ticketService, TenantService tenantService, PriorityMapper priorityMapper) {
        this.priorityRepository = priorityRepository;
        this.ticketService = ticketService;
        this.tenantService = tenantService;
        this.priorityMapper = priorityMapper;
    }

    public Priority create(PriorityDto data){
        Priority priority = priorityMapper.toEntity(data);
        return priorityRepository.save(priority);
    }

    public Priority getById( Long id) {
        return priorityRepository.findById(id).orElseThrow(() -> new RuntimeException("Priority com ID " + id + " não encontrado."));
    }
}
