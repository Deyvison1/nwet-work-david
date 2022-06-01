package api.networkn.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import api.networkn.models.Category;
import api.networkn.models.dtos.CategoryDTO;

public interface ICategoryService {

	Page<Category> getAll(Pageable pageable);
	
	List<CategoryDTO> getAll();
	
	CategoryDTO insert(Category category);
	
	CategoryDTO update(CategoryDTO categoryDTO);
	
	void delete(Long categoryId);
	
	Long contarTodos();
}
