package api.networkn.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import api.networkn.models.Category;
import api.networkn.models.dtos.CategoryDTO;
import api.networkn.models.dtos.LabelValueDTO;

public interface ICategoryService {

	Page<Category> getAll(Pageable pageable);
	
	List<LabelValueDTO> getAll();
	
	CategoryDTO insert(Category category);
	
	CategoryDTO update(Category category);
	
	void delete(Long categoryId);
	
	Long contarTodos();
}
