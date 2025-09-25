package api_rest.zoologico.Application.Controllers;

import api_rest.zoologico.Application.DTOs.TicketRequestDTO;
import api_rest.zoologico.Domain.Models.Ticket;
import api_rest.zoologico.Domain.Services.TicketService;
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

    @GetMapping("/especies")
    public ResponseEntity<List<Ticket>> getBySpecies(@RequestParam String data) {
        return ResponseEntity.ok().body(TicketService.getBySpecies(data));
    }

    @GetMapping("/name")
    public ResponseEntity<List<Ticket>> getByName(@RequestParam String data) {
        return ResponseEntity.ok().body(TicketService.getByName(data));
    }

    @GetMapping("/idade")
    public ResponseEntity<List<Ticket>> getByAge(
            @RequestParam Integer min,
            @RequestParam Integer max) {
        return ResponseEntity.ok().body(TicketService.getByAge(min, max));
    }

    @GetMapping("/{id}")
    public Ticket getById(@PathVariable Long id) {
        return TicketService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody TicketRequestDTO data) {
        return ResponseEntity.ok().body(TicketService.create(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable Long id, @RequestBody TicketRequestDTO data) {
        return ResponseEntity.ok().body(TicketService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
            TicketService.delete(id);
            return ResponseEntity.noContent().build();
    }
}