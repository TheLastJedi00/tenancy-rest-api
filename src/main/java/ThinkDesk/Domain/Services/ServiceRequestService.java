package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.ServiceRequestDto;
import ThinkDesk.Domain.Models.ServiceRequest;
import ThinkDesk.Domain.Models.Tenant;
import ThinkDesk.Domain.Repositories.ServiceRequestRepository;
import ThinkDesk.Infra.Mapper.ServiceRequestMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRequestService {
    private final ServiceRequestRepository serviceRequestRepository;
    private final ServiceRequestMapper serviceRequestMapper;
    private final TenantService tenantService;

    public ServiceRequestService(ServiceRequestRepository serviceRequestRepository, ServiceRequestMapper serviceRequestMapper, TenantService tenantService) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.serviceRequestMapper = serviceRequestMapper;
        this.tenantService = tenantService;
    }

    public ServiceRequest create(ServiceRequestDto data) {
        ServiceRequest serviceRequest = serviceRequestMapper.toEntity(data);
        Tenant tenant = tenantService.getById(data.tenant_id());
        serviceRequest.setTenant(tenant);
        return serviceRequestRepository.save(serviceRequest);
    }

    public List<ServiceRequest> getAll(){
        return serviceRequestRepository.findAll();
    }

    public ServiceRequest getById(Long id){
        return serviceRequestRepository.getReferenceById(id);
    }

    public ServiceRequest update(Long id, ServiceRequestDto data){
        return serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service Request Not Found"));
    }

    public ServiceRequest delete(Long id){
        ServiceRequest serviceRequest = serviceRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("Service Request Not Found"));
        serviceRequestRepository.delete(serviceRequest);
        return serviceRequest;
    }
}
