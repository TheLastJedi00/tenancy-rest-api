package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.ServiceRequestDto;
import ThinkDesk.Application.DTOs.TicketDTO;
import ThinkDesk.Domain.Models.ServiceRequest;
import ThinkDesk.Domain.Models.Ticket;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ServiceRequestMapper {
    ServiceRequest toEntity(ServiceRequestDto serviceRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ServiceRequestDto serviceRequestDto, @MappingTarget ServiceRequest serviceRequest);
}
