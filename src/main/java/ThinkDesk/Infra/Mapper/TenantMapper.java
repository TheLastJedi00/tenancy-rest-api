package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.TenantDto;
import ThinkDesk.Domain.Models.Tenant;
import org.mapstruct.*;

@Mapper
public interface TenantMapper {
    Tenant toEntity(TenantDto tenantDto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(TenantDto tenantDto, @MappingTarget Tenant tenant);
}
