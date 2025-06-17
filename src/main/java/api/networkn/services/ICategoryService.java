package api.networkn.services;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import api.networkn.exception.UnsupportedFileException;
import api.networkn.models.Category;
import api.networkn.models.dtos.CategoryDTO;

public interface ICategoryService {

	Page<Category> getAll(Pageable pageable);

	List<CategoryDTO> getAll();

	CategoryDTO insert(Category category);

	CategoryDTO update(CategoryDTO categoryDTO);

	void delete(Long categoryId);

	CategoryDTO findByIdDTO(Long id);

	Long contarTodos();

	List<CategoryDTO> massCreation(MultipartFile file);

	Resource exportPage(Pageable pageable, String acceptHeader);
}
