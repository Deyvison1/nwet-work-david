package api.networkn.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import api.networkn.models.Category;
import api.networkn.models.dtos.CategoryDTO;

public interface ICategoryService {

	Page<Category> getAll(Pageable pageable);
	
	CategoryDTO insert(Category category);
	
	CategoryDTO update(Category category);
	
	void delete(Long categoryId);
	
	Long contarTodos();
}
