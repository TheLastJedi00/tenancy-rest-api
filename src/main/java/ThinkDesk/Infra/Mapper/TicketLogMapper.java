package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.TicketLogDTO;
import ThinkDesk.Domain.Models.TicketLog;
import org.mapstruct.*;

@Mapper
public interface TicketLogMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(TicketLogDTO ticketLogDTO, @MappingTarget TicketLog ticketLog);
}
