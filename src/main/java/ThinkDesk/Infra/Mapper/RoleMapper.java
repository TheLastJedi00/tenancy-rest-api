package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.RoleDto;
import ThinkDesk.Domain.Models.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toEntity(RoleDto dto);
}
