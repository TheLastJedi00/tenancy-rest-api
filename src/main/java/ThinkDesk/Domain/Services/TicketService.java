package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.TicketDTO;
import ThinkDesk.Application.DTOs.TicketKeysDto;
import ThinkDesk.Application.DTOs.TicketUpdateDto;
import ThinkDesk.Domain.Models.Enums.TicketStatus;
import ThinkDesk.Domain.Models.Ticket;
import ThinkDesk.Domain.Repositories.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CategoryService categoryService;
    private final TechnicianService technicianService;
    private final PriorityService priorityService;
    private final TenantService tenantService;
    private final UserService userService;
    private final TranslationService translationService;

    public TicketService(TicketRepository ticketRepository, CategoryService categoryService, TechnicianService technicianService, PriorityService priorityService, TenantService tenantService, UserService userService, TranslationService translationService) {
        this.ticketRepository = ticketRepository;
        this.categoryService = categoryService;
        this.technicianService = technicianService;
        this.priorityService = priorityService;
        this.tenantService = tenantService;
        this.userService = userService;
        this.translationService = translationService;
    }

    public Ticket getById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket com ID " + id + " não encontrado."));
    }

    public Ticket update(Long id, TicketUpdateDto data) {
        TicketKeysDto ticketKeys = new TicketKeysDto(
                categoryService.getById(data.category()),
                technicianService.getById(data.technician()),
                tenantService.getById(data.tenant()),
                userService.getById(data.requester()),
                priorityService.getById(data.priority())
        );
        Ticket foundTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket com ID " + id + " não encontrado"));

        Ticket ticket = foundTicket.update(data, ticketKeys);

        return ticketRepository.save(ticket);
    }

    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

    public Ticket create(TicketDTO data){
        TicketKeysDto ticketKeys = new TicketKeysDto(
                categoryService.getById(data.category()),
                technicianService.getById(data.technician()),
                tenantService.getById(data.tenant()),
                userService.getById(data.requester()),
                priorityService.getById(data.priority())
        );
        Ticket ticket = new Ticket(data, ticketKeys);
        ticket.setTranslatedDescription(
                translationService.translate(data.description()));
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
