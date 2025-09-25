package api_rest.zoologico.Infra.Mapper;

import api_rest.zoologico.Application.DTOs.TicketDTO;
import api_rest.zoologico.Domain.Models.Ticket;
import org.mapstruct.*;

@Mapper
public interface TicketMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(TicketDTO animalRequestDTO, @MappingTarget Ticket animal);
}
