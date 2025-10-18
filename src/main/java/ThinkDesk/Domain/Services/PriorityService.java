package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.PriorityDto;
import ThinkDesk.Domain.Models.Priority;
import ThinkDesk.Domain.Models.Tenant;
import ThinkDesk.Domain.Repositories.PriorityRepository;
import org.springframework.stereotype.Service;

@Service
public class PriorityService {
    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public Priority create(PriorityDto data, Tenant tenant){
        return priorityRepository.save(new Priority(data, tenant));
    }

    public Priority getById(Long id) {
        return priorityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prioridade com id " + id + " não encontrada"));
    }
}
