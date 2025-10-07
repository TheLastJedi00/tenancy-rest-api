package ThinkDesk.Application.Controllers;

import ThinkDesk.Application.DTOs.SlaPolicyDTO;
import ThinkDesk.Application.DTOs.SlaPolicyResponseDTO;
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
    public ResponseEntity<Page<SlaPolicyResponseDTO>> getAll(
            @PageableDefault(size = 15, sort = {"name"}) Pageable pageable) {

        Page<SlaPolicy> slaPolicyPage = slaPolicyService.getAll(pageable);
        Page<SlaPolicyResponseDTO> responseDtoPage = slaPolicyPage.map(slaPolicyMapper::toResponseDTO);

        return ResponseEntity.ok(responseDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlaPolicyResponseDTO> getById(@PathVariable Long id) {
        SlaPolicy slaPolicy = slaPolicyService.getById(id);
        SlaPolicyResponseDTO responseDto = slaPolicyMapper.toResponseDTO(slaPolicy);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<Page<SlaPolicyResponseDTO>> getAllByTenant(
            @PathVariable Long tenantId,
            @PageableDefault(size = 15, sort = {"name"}) Pageable pageable) {

        Page<SlaPolicy> slaPolicyPage = slaPolicyService.getAllByTenant(tenantId, pageable);
        Page<SlaPolicyResponseDTO> responseDtoPage = slaPolicyPage.map(slaPolicyMapper::toResponseDTO);

        return ResponseEntity.ok(responseDtoPage);
    }

    @PostMapping
    public ResponseEntity<SlaPolicyResponseDTO> create(@RequestBody @Valid SlaPolicyDTO data) {
        SlaPolicy newPolicy = slaPolicyService.create(data);
        return ResponseEntity.ok(slaPolicyMapper.toResponseDTO(newPolicy));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlaPolicyResponseDTO> update(@PathVariable Long id, @RequestBody @Valid SlaPolicyDTO data) {
        SlaPolicy updatedPolicy = slaPolicyService.update(id, data);
        return ResponseEntity.ok(slaPolicyMapper.toResponseDTO(updatedPolicy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
            slaPolicyService.delete(id);
            return ResponseEntity.noContent().build();
    }
}