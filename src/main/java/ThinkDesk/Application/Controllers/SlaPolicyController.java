package ThinkDesk.Application.Controllers;

import ThinkDesk.Application.DTOs.SlaPolicyDTO;
import ThinkDesk.Domain.Models.SlaPolicy;
import ThinkDesk.Domain.Services.SlaPolicyService;
import ThinkDesk.Infra.Mapper.SlaPolicyMapper;
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
    private final SlaPolicyMapper slaPolicyMapper;


    public SlaPolicyController(SlaPolicyService SlaPolicyService, SlaPolicyMapper slaPolicyMapper) {
        this.slaPolicyService = SlaPolicyService;
        this.slaPolicyMapper = slaPolicyMapper;
    }

    @GetMapping
    public ResponseEntity<Page<SlaPolicy>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(slaPolicyService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public SlaPolicy getById(@PathVariable Long id) {
        return slaPolicyService.getById(id);
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<Page<SlaPolicyDTO>> getAllByTenant(
            @PathVariable Long tenantId,
            @PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {

        Page<SlaPolicy> slaPolicyPage = slaPolicyService.getAllByTenant(tenantId, pageable);
        Page<SlaPolicyDTO> responseDtoPage = slaPolicyPage.map(slaPolicyMapper::toDto);

        return ResponseEntity.ok(responseDtoPage);
    }

    @PostMapping
    public ResponseEntity<SlaPolicy> create(@RequestBody @Valid SlaPolicyDTO data) {
        return ResponseEntity.ok().body(slaPolicyService.create(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlaPolicy> update(@PathVariable Long id, @RequestBody @Valid SlaPolicyDTO data) {
        return ResponseEntity.ok().body(slaPolicyService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
            slaPolicyService.delete(id);
            return ResponseEntity.noContent().build();
    }
}