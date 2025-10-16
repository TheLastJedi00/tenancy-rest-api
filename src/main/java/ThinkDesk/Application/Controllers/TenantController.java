package ThinkDesk.Application.Controllers;

import ThinkDesk.Application.DTOs.TenantDto;
import ThinkDesk.Domain.Models.Tenant;
import ThinkDesk.Domain.Services.TenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenants")
public class TenantController {
    private final TenantService tenantService;
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public ResponseEntity<Tenant> create(@RequestBody TenantDto tenantDto) {
        return ResponseEntity.ok(tenantService.create(tenantDto));
    }
    @GetMapping
    public ResponseEntity<List<Tenant>> getAll() {
        return ResponseEntity.ok(tenantService.getAll());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tenantService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Tenant> update(@PathVariable Long id, @RequestBody TenantDto tenantDto) {
        return ResponseEntity.ok(tenantService.update(id, tenantDto));
    }
}
