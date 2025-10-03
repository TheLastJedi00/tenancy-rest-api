package ThinkDesk.Application.Controllers;

import ThinkDesk.Application.DTOs.TechnicianDTO;
import ThinkDesk.Domain.Models.Technician;
import ThinkDesk.Domain.Services.TechnicianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technicians")
public class TechnicianController {

    private final TechnicianService TechnicianService;

    public TechnicianController(TechnicianService TechnicianService) {
        this.TechnicianService = TechnicianService;
    }

    @GetMapping
    public ResponseEntity<List<Technician>> getAll() {
        return ResponseEntity.ok().body(TechnicianService.getAll());
    }

    @GetMapping("/{id}")
    public Technician getById(@PathVariable Long id) {
        return TechnicianService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Technician> create(@RequestBody TechnicianDTO data) {
        return ResponseEntity.ok().body(TechnicianService.create(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technician> update(@PathVariable Long id, @RequestBody TechnicianDTO data) {
        return ResponseEntity.ok().body(TechnicianService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        TechnicianService.delete(id);
        return ResponseEntity.noContent().build();
    }
}