package ThinkDesk.Application.Controllers;

import ThinkDesk.Application.DTOs.TicketDTO;
import ThinkDesk.Domain.Models.Ticket;
import ThinkDesk.Domain.Services.TicketService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService TicketService) {
        this.ticketService = TicketService;
    }

    @GetMapping
    public ResponseEntity<Page<Ticket>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(ticketService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public Ticket getById(@PathVariable Long id) {
        return ticketService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody @Valid TicketDTO data) {
        return ResponseEntity.ok().body(ticketService.create(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable Long id, @RequestBody @Valid TicketDTO data) {
        return ResponseEntity.ok().body(ticketService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
            ticketService.delete(id);
            return ResponseEntity.noContent().build();
    }
}