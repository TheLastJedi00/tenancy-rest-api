package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.TechnicianDTO;
import ThinkDesk.Domain.Models.Technician;
import org.mapstruct.*;

@Mapper
public interface TechnicianMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(TechnicianDTO tenantDto, @MappingTarget Technician technician);
}
