package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.SlaPolicyDTO;
import ThinkDesk.Application.DTOs.SlaPolicyKeysDto;
import ThinkDesk.Domain.Models.SlaPolicy;
import ThinkDesk.Domain.Repositories.SlaPolicyRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SlaPolicyService {

    private final SlaPolicyRepository slaPolicyRepository;
    private final TenantService tenantService;
    private final CategoryService categoryService;
    private final PriorityService priorityService;

    public SlaPolicyService(SlaPolicyRepository slaPolicyRepository, TenantService tenantService, CategoryService categoryService, PriorityService priorityService) {
        this.slaPolicyRepository = slaPolicyRepository;
        this.tenantService = tenantService;
        this.categoryService = categoryService;
        this.priorityService = priorityService;
    }

    public SlaPolicy getById(Long id) {
        return slaPolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SlaPolicy com ID " + id + " não encontrado."));
    }

    public SlaPolicy update(Long id, SlaPolicyDTO data) {
        SlaPolicy found = getById(id);
        SlaPolicy slaPolicy = found.update(data);
        return slaPolicyRepository.save(slaPolicy);
    }

    public void delete(Long id) {
        slaPolicyRepository.deleteById(id);
    }

    public SlaPolicy create(SlaPolicyDTO data) {
        SlaPolicyKeysDto keys = new SlaPolicyKeysDto(
                tenantService.getById(data.tenantId()),
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

    public SlaPolicy getByTenantAndCategoryAndPriority(Long tenant,Long category,Long priority) {
        return slaPolicyRepository.findByTenantIdAndCategoryIdAndPriorityId(tenant, category, priority)
                .orElseThrow(() -> new RuntimeException("SlaPolicy não encontrada"));

    }
}