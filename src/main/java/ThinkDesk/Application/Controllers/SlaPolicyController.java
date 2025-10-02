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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/slapolicies")
public class SlaPolicyController {

    private final SlaPolicyService slaPolicyService;
    private final SlaPolicyMapper slaPolicyMapper;

    public SlaPolicyController(SlaPolicyService slaPolicyService, SlaPolicyMapper slaPolicyMapper) {
        this.slaPolicyService = slaPolicyService;
        this.slaPolicyMapper = slaPolicyMapper;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<SlaPolicyResponseDTO> create(@RequestBody @Valid SlaPolicyDTO data, UriComponentsBuilder uriBuilder) {
        SlaPolicy newSlaPolicy = slaPolicyService.create(data);
        SlaPolicyResponseDTO responseDto = slaPolicyMapper.toResponseDto(newSlaPolicy);

        URI uri = uriBuilder.path("/slapolicies/{id}").buildAndExpand(newSlaPolicy.getId()).toUri();

        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlaPolicyResponseDTO> getById(@PathVariable Long id) {
        SlaPolicy slaPolicy = slaPolicyService.getById(id);
        return ResponseEntity.ok(slaPolicyMapper.toResponseDto(slaPolicy));
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<Page<SlaPolicyResponseDTO>> getAllByTenant(
            @PathVariable Long tenantId,
            @PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {

        Page<SlaPolicy> slaPolicyPage = slaPolicyService.getAllByTenant(tenantId, pageable);
        Page<SlaPolicyResponseDTO> responseDtoPage = slaPolicyPage.map(slaPolicyMapper::toResponseDto);

        return ResponseEntity.ok(responseDtoPage);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<SlaPolicyResponseDTO> update(@PathVariable Long id, @RequestBody @Valid SlaPolicyDTO data) {
        SlaPolicy updatedSlaPolicy = slaPolicyService.update(id, data);
        return ResponseEntity.ok(slaPolicyMapper.toResponseDto(updatedSlaPolicy));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        slaPolicyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}