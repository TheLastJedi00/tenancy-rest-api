package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.PriorityDto;
import ThinkDesk.Domain.Models.Priority;
import org.mapstruct.Mapper;

@Mapper
public interface PriorityMapper {
    Priority toEntity(PriorityDto priorityDto);
}
