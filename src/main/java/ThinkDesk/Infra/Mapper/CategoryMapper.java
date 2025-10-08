package ThinkDesk.Infra.Mapper;

import ThinkDesk.Application.DTOs.CategoryDto;
import ThinkDesk.Application.DTOs.SlaPolicyDTO;
import ThinkDesk.Domain.Models.Category;
import ThinkDesk.Domain.Models.SlaPolicy;
import org.mapstruct.*;

@Mapper
public interface CategoryMapper {
    Category toEntity(CategoryDto data);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CategoryDto data, @MappingTarget Category category);
}
