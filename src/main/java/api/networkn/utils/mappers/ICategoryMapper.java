package api.networkn.utils.mappers;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import api.networkn.models.Category;
import api.networkn.models.dtos.CategoryDTO;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {

	CategoryDTO categoryToCategoryDTO(Category category);

	List<CategoryDTO> toList(List<Category> category);

}
