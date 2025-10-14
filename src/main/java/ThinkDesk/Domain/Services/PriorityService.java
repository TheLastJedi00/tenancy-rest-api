package ThinkDesk.Domain.Services;

import ThinkDesk.Domain.Models.Priority;
import ThinkDesk.Domain.Repositories.PriorityRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class PriorityService {
    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public @NotNull Priority getById(@NotBlank Long id) {
        return priorityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prioridade não encontrada"));
    }
}
