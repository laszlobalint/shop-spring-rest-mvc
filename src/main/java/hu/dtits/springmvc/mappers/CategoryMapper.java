package hu.dtits.springmvc.mappers;

import hu.dtits.springmvc.domains.Category;
import hu.dtits.springmvc.models.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryDtoToCategory(CategoryDTO categoryDTO);

    CategoryDTO categoryToCategoryDTO(Category category);
}
