package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.UserRequestDto;
import ThinkDesk.Domain.Models.User;
import org.mapstruct.*;

@Mapper(componentModel = "Spring")

public interface UserMapper {
    User toEntity(UserRequestDto userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UserRequestDto userDto, @MappingTarget User user);

}
