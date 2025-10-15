package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.PriorityDto;
import ThinkDesk.Domain.Models.Priority;
import ThinkDesk.Domain.Repositories.PriorityRepository;
import ThinkDesk.Infra.Mapper.PriorityMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class PriorityService {
    private final PriorityRepository priorityRepository;
    private final PriorityMapper priorityMapper;

    public PriorityService(PriorityRepository priorityRepository, PriorityMapper priorityMapper) {
        this.priorityRepository = priorityRepository;
        this.priorityMapper = priorityMapper;
    }

    public Priority create(PriorityDto priorityDto){
        return priorityRepository.save(priorityMapper.toEntity(priorityDto));
    }
}
