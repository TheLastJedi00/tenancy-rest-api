package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.TicketDTO;
import ThinkDesk.Application.DTOs.TicketUpdateDto;
import ThinkDesk.Domain.Models.Enums.TicketStatus;
import ThinkDesk.Infra.Mapper.TicketMapper;
import ThinkDesk.Domain.Models.Ticket;
import ThinkDesk.Domain.Repositories.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CategoryService categoryService;
    private final TechnicianService technicianService;
    private final PriorityService priorityService;
    private final TenantService tenantService;
    private final UserService userService;
    private final TicketMapper ticketMapper;
    private final TranslationService translationService;

    public TicketService(TicketRepository ticketRepository, CategoryService categoryService, TechnicianService technicianService, PriorityService priorityService, TenantService tenantService, UserService userService, TicketMapper ticketMapper, TranslationService translationService) {
        this.ticketRepository = ticketRepository;
        this.categoryService = categoryService;
        this.technicianService = technicianService;
        this.priorityService = priorityService;
        this.tenantService = tenantService;
        this.userService = userService;
        this.ticketMapper = ticketMapper;
        this.translationService = translationService;
    }

    public Ticket getById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket com ID " + id + " não encontrado."));
    }

    public Ticket update(Long id, TicketUpdateDto data) {
        Ticket ticket = getById(id);
        ticket.update(data);
        if (data.category() != null) ticket.setCategory(categoryService.getById(data.category()));
        if (data.technician() != null) ticket.setTechnician(technicianService.getById(data.technician()));
        if (data.tenant() != null) ticket.setTenant(tenantService.getById(data.tenant()));
        if (data.requester() != null) ticket.setRequester(userService.getById(data.requester()));
        if (data.priority() != null) ticket.setPriority(priorityService.getById(data.priority()));

        return ticketRepository.save(ticket);
    }

    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

    public Ticket create(TicketDTO data) {
        Ticket ticket = new Ticket(data);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setCategory(categoryService.getById(data.category()));
        ticket.setTechnician(technicianService.getById(data.technician()));
        ticket.setTenant(tenantService.getById(data.tenant()));
        ticket.setRequester(userService.getById(data.requester()));
        String translatedDescription = translationService.translate(ticket.getDescription());
        ticket.setTranslatedDescription(translatedDescription);
        ticket.setPriority(priorityService.getById(data.priority()));

        return ticketRepository.save(ticket);
    }

    public Page<Ticket> getAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    public List<Ticket> getResolvedTicketByTeam(List<Long> membersId, TicketStatus status){
        return ticketRepository.findByTechnicianIdInAndStatus(membersId, status);
    }

    public List<Ticket> getOpenTicketsByTeam(List<Long> membersId, TicketStatus status){
        return ticketRepository.findByTechnicianIdInAndStatusNot(membersId, status);
    }

    public List<Ticket> getResolvedTicketByEmployee(Long employeeId, TicketStatus status){
        return ticketRepository.findByTechnicianIdAndStatus(employeeId, status);
    }

    public List<Ticket> getOpenTicketsByEmployee(Long employeeId, TicketStatus status){
        return ticketRepository.findByTechnicianIdAndStatusNot(employeeId, status);
    }
}
