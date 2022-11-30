package api.networkn.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import api.networkn.models.Category;
import api.networkn.models.dtos.CategoryDTO;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper extends BaseEntityMapper<Category, CategoryDTO> {
}
