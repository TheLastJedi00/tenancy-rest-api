package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.SlaPolicyDTO;
import ThinkDesk.Domain.Models.Enums.TicketStatus;
import ThinkDesk.Domain.Models.SlaPolicy;
import ThinkDesk.Domain.Models.Tenant;
import ThinkDesk.Domain.Repositories.SlaPolicyRepository;
import ThinkDesk.Domain.Repositories.TenantRepository;
import ThinkDesk.Infra.Mapper.SlaPolicyMapper;
import jakarta.persistence.EntityExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SlaPolicyService {

    private final SlaPolicyRepository slaPolicyRepository;
    private final TenantRepository tenantRepository;
    private final SlaPolicyMapper slaPolicyMapper;

    public SlaPolicyService(SlaPolicyRepository slaPolicyRepository, TenantRepository tenantRepository, SlaPolicyMapper slaPolicyMapper) {
        this.slaPolicyRepository = slaPolicyRepository;
        this.tenantRepository = tenantRepository;
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

    public SlaPolicy create(SlaPolicyDTO dto) {
        if (slaPolicyRepository.existsByTenantIdAndPriority(dto.tenantId(), dto.priority())) {
            throw new EntityExistsException("Já existe uma política de SLA para esta prioridade neste tenant.");
        }

        Tenant tenant = tenantRepository.findById(dto.tenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Tenant com ID " + dto.tenantId() + " não encontrado."));

        SlaPolicy slaPolicy = slaPolicyMapper.toEntity(dto);
        slaPolicy.setTenant(tenant);

        return slaPolicyRepository.save(slaPolicy);
    }

    public Page<SlaPolicy> getAll(Pageable pageable) {
        return slaPolicyRepository.findAll(pageable);
    }

    public Page<SlaPolicy> getAllByTenant(Long tenantId, Pageable pageable) {
        return slaPolicyRepository.findByTenantId(tenantId, pageable);
    }
}