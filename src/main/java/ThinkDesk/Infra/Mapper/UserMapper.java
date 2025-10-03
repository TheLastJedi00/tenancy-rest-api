package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.UserDto;
import ThinkDesk.Domain.Models.User;
import org.mapstruct.*;

@Mapper(componentModel = "Spring")

public interface UserMapper {
    User toEntity(UserDto userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UserDto userDto, @MappingTarget User user);

}
