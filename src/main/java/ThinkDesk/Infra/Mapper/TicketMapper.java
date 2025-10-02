package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.TicketDTO;
import ThinkDesk.Domain.Models.Ticket;
import org.mapstruct.*;

@Mapper
public interface TicketMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(TicketDTO ticketDTO, @MappingTarget Ticket ticket);
}
