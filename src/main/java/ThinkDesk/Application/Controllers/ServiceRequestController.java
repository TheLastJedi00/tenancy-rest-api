package ThinkDesk.Application.Controllers;

import ThinkDesk.Application.DTOs.ServiceRequestDto;
import ThinkDesk.Domain.Models.ServiceRequest;
import ThinkDesk.Domain.Services.ServiceRequestService;
import jakarta.validation.Valid;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-request")
public class ServiceRequestController {
    private final ServiceRequestService serviceRequestService;

    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @PostMapping
    public ResponseEntity<ServiceRequest> create(@Valid @RequestBody ServiceRequestDto data){
        return ResponseEntity.ok(serviceRequestService.create(data));
    }

    @GetMapping
    public ResponseEntity<List<ServiceRequest>> getAll(){
        return ResponseEntity.ok(serviceRequestService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequest> getById(@PathVariable Long id){
        return ResponseEntity.ok(serviceRequestService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRequest> update(@PathVariable Long id, @RequestBody ServiceRequestDto data){
        return ResponseEntity.ok(serviceRequestService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceRequest> delete(@PathVariable Long id) {
        return ResponseEntity.ok(serviceRequestService.delete(id));
    }
}
