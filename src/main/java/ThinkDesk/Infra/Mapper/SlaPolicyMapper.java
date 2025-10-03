package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.SlaPolicyDTO;
import ThinkDesk.Domain.Models.SlaPolicy;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SlaPolicyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenant", ignore = true)
    SlaPolicy toEntity(SlaPolicyDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenant", ignore = true)
    void updateEntityFromDto(SlaPolicyDTO dto, @MappingTarget SlaPolicy slaPolicy);
}