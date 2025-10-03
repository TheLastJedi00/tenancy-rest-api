package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.TicketDTO;
import ThinkDesk.Domain.Models.Enums.TicketStatus;
import ThinkDesk.Infra.Mapper.TicketMapper;
import ThinkDesk.Domain.Models.Ticket;
import ThinkDesk.Domain.Repositories.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;

    }

    public Ticket getById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket com ID " + id + " não encontrado."));
    }

    public Ticket update(Long id, TicketDTO dto) {
        Ticket ticket = getById(id);
        ticketMapper.updateEntityFromDto(dto, ticket);
        return ticketRepository.save(ticket);
    }

    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

    public Ticket create(TicketDTO dto) {
        Ticket ticket = ticketMapper.toEntity(dto);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public Page<Ticket> getAll(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }
}