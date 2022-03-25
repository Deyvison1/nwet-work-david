package api.networkn.web.rest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import api.networkn.models.Category;
import api.networkn.models.dtos.CategoryDTO;
import api.networkn.models.dtos.LabelValueDTO;
import api.networkn.services.ICategoryService;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", exposedHeaders = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
	
	private final ICategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAll(Pageable pageable) {
		Page<Category> listCategory = categoryService.getAll(pageable);
		final Long total = categoryService.contarTodos();

		HttpHeaders headers = new HttpHeaders();
		headers.add("X_TOTAL_COUNT", String.valueOf(total));
		return new ResponseEntity<List<Category>>(listCategory.getContent(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/get-all")
	public List<LabelValueDTO> getAll() {
		return categoryService.getAll();
	}
	
	@PostMapping
	public CategoryDTO insert(@RequestBody Category category) {
		return categoryService.insert(category);
	}
	
	@PutMapping
	public CategoryDTO update(@RequestBody Category category) {
		return categoryService.update(category);
	}
	
	@DeleteMapping("/{categoryId}")
	public void delete(@PathVariable Long categoryId) {
		categoryService.delete(categoryId);
	}

}
