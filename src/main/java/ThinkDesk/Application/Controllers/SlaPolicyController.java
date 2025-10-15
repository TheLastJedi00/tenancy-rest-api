package ThinkDesk.Application.Controllers;

import ThinkDesk.Application.DTOs.SlaPolicyDTO;
import ThinkDesk.Domain.Models.SlaPolicy;
import ThinkDesk.Domain.Services.SlaPolicyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/slapolicies")
public class SlaPolicyController {
    private final SlaPolicyService slaPolicyService;


    public SlaPolicyController(SlaPolicyService SlaPolicyService) {
        this.slaPolicyService = SlaPolicyService;
    }

    @GetMapping
    public ResponseEntity<Page<SlaPolicy>> getAll(
            @PageableDefault(size = 15, sort = {"name"}) Pageable pageable) {

        return ResponseEntity.ok(slaPolicyService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlaPolicy> getById(@PathVariable Long id) {
        return ResponseEntity.ok(slaPolicyService.getById(id));
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<Page<SlaPolicy>> getAllByTenant(
            @PathVariable Long tenantId,
            @PageableDefault(size = 15, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(slaPolicyService.getAllByTenant(tenantId, pageable));
    }

    @PostMapping
    public ResponseEntity<SlaPolicy> create(@RequestBody @Valid SlaPolicyDTO data) {
        return ResponseEntity.ok(slaPolicyService.create(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlaPolicy> update(@PathVariable Long id, @RequestBody @Valid SlaPolicyDTO data) {
        return ResponseEntity.ok(slaPolicyService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
            slaPolicyService.delete(id);
            return ResponseEntity.noContent().build();
    }
}