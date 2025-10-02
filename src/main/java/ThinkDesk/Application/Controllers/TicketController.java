package ThinkDesk.Application.Controllers;

import ThinkDesk.Application.DTOs.TicketDTO;
import ThinkDesk.Domain.Models.Ticket;
import ThinkDesk.Domain.Services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Tickets")
public class TicketController {

    private final TicketService TicketService;

    public TicketController(TicketService TicketService) {
        this.TicketService = TicketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAll() {
        return ResponseEntity.ok().body(TicketService.getAll());
    }

    @GetMapping("/{id}")
    public Ticket getById(@PathVariable Long id) {
        return TicketService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody TicketDTO data) {
        return ResponseEntity.ok().body(TicketService.create(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable Long id, @RequestBody TicketDTO data) {
        return ResponseEntity.ok().body(TicketService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
            TicketService.delete(id);
            return ResponseEntity.noContent().build();
    }
}