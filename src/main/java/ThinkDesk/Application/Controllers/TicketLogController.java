package ThinkDesk.Application.Controllers;

import ThinkDesk.Application.DTOs.TicketLogDTO;
import ThinkDesk.Domain.Models.TicketLog;
import ThinkDesk.Domain.Services.TicketLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticketlog")
public class TicketLogController {
    private final TicketLogService ticketLogService;
    public TicketLogController(TicketLogService ticketLogService) {this.ticketLogService = ticketLogService;}

    @PostMapping
    public ResponseEntity<TicketLog> create(@RequestBody TicketLogDTO dto) {
        return ResponseEntity.ok(ticketLogService.create(dto));
    }
    @GetMapping
    public ResponseEntity<List<TicketLog>> getAll() {
        return ResponseEntity.ok(ticketLogService.getAll());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<TicketLog> update(@PathVariable Long id, @RequestBody TicketLogDTO dto) {
        return ResponseEntity.ok(ticketLogService.update(id, dto));
    }
}
