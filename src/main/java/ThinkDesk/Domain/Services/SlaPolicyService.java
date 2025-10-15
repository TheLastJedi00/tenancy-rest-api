package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.CategoryDto;
import ThinkDesk.Application.DTOs.SlaPolicyDTO;
import ThinkDesk.Application.DTOs.SlaPolicyKeysDto;
import ThinkDesk.Domain.Models.Category;
import ThinkDesk.Domain.Models.SlaPolicy;
import ThinkDesk.Domain.Models.Tenant;
import ThinkDesk.Domain.Repositories.SlaPolicyRepository;
import ThinkDesk.Domain.Repositories.TenantRepository;
import ThinkDesk.Infra.Exception.ResourceNotFoundException;
import ThinkDesk.Infra.Mapper.SlaPolicyMapper;
import jakarta.persistence.EntityExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SlaPolicyService {

    private final SlaPolicyRepository slaPolicyRepository;
    private final TenantService tenantService;
    private final CategoryService categoryService;
    private final PriorityService priorityService;
    private final SlaPolicyMapper slaPolicyMapper;

    public SlaPolicyService(SlaPolicyRepository slaPolicyRepository, TenantService tenantService, CategoryService categoryService, PriorityService priorityService, SlaPolicyMapper slaPolicyMapper) {
        this.slaPolicyRepository = slaPolicyRepository;
        this.tenantService = tenantService;
        this.categoryService = categoryService;
        this.priorityService = priorityService;
        this.slaPolicyMapper = slaPolicyMapper;
    }

    public SlaPolicy getById(Long id) {
        return slaPolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SlaPolicy com ID " + id + " não encontrado."));
    }

    public SlaPolicy update(Long id, SlaPolicyDTO dto) {
        SlaPolicy slaPolicy = getById(id);
        slaPolicyMapper.updateEntityFromDto(dto, slaPolicy);
        return slaPolicyRepository.save(slaPolicy);
    }

    public void delete(Long id) {
        slaPolicyRepository.deleteById(id);
    }

    public SlaPolicy create(SlaPolicyDTO data) {
        SlaPolicyKeysDto keys = new SlaPolicyKeysDto(
                tenantService.create(data.tenantId()),
                categoryService.create(data.categoryDto()),
                priorityService.create(data.priorityDto())
        );
        SlaPolicy slaPolicy = new SlaPolicy(data, keys);
        return slaPolicyRepository.save(slaPolicy);
    }
    public Page<SlaPolicy> getAll(Pageable pageable) {
        return slaPolicyRepository.findAll(pageable);
    }

    public Page<SlaPolicy> getAllByTenant(Long tenantId, Pageable pageable) {
        return slaPolicyRepository.findByTenantId(tenantId, pageable);
    }
}