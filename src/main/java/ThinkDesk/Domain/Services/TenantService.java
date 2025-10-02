package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.TenantDto;
import ThinkDesk.Domain.Models.Tenant;
import ThinkDesk.Domain.Repositories.TenantRepository;
import ThinkDesk.Infra.Mapper.TenantMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    public TenantService(TenantRepository tenantRepository, TenantMapper tenantMapper) {
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
    }

    public Tenant create(TenantDto tenantDto) {
        return tenantRepository.save(new Tenant());
    }

    public List<Tenant> getAll() {
        return tenantRepository.findAll();
    }

    public Tenant getById(Long id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant com " + id + " não encontrado."));
    }

    public Tenant update(Long id, TenantDto tenantDto) {
        Tenant tenant = getById(id);
        tenantMapper.updateEntityFromDto(tenantDto, tenant);
        //CNJP API
        return tenantRepository.save(tenant);
    }

    public void delete(Long id) {
        tenantRepository.deleteById(id);
    }

}

