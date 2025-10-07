package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.RoleDto;
import ThinkDesk.Domain.Models.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    Role toEntity(RoleDto dto);
}
