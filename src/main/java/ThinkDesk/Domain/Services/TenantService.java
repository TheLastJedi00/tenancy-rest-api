package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.CnpjDto;
import ThinkDesk.Application.DTOs.TenantDto;
import ThinkDesk.Domain.Models.Tenant;
import ThinkDesk.Domain.Repositories.TenantRepository;
import ThinkDesk.Infra.Mapper.TenantMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;
    private final CnpjService cnpjService;

    public TenantService(TenantRepository tenantRepository, TenantMapper tenantMapper, CnpjService cnpjService) {
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
        this.cnpjService = cnpjService;
    }

    public Tenant create(TenantDto data) {
        Tenant existingTenant = tenantRepository.getReferenceByTaxID(data.taxID());
        if (existingTenant != null) {
            throw new RuntimeException("CNPJ já está sendo usado por outro Tenant");
        }
        String cnpj = data.taxID();
        CnpjDto cnpjResponse = cnpjService.getTenantByCnpj(cnpj);
        if(cnpjResponse.razaoSocial() == null) throw  new RuntimeException("CNPJ not found");
        Tenant tenant = tenantMapper.toEntity(data);
        tenant.setLegalName(cnpjResponse.razaoSocial());
        tenant.setCreatedAt(LocalDateTime.now());
        tenant.setActive(true);
        return tenantRepository.save(tenant);
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

    public Tenant delete(Long id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant com " + id + " não encontrado."));
        tenantRepository.deleteById(tenant.getId());
        return tenant;
    }

}

