package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.TenantDto;
import ThinkDesk.Domain.Models.Tenant;
import org.mapstruct.*;

@Mapper
public interface TenantMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(TenantDto tenantDto, @MappingTarget Tenant tenant);
}
