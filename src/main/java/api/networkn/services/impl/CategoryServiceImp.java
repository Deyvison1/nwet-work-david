package api.networkn.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import api.networkn.exception.NotFoundException;
import api.networkn.models.Category;
import api.networkn.models.dtos.CategoryDTO;
import api.networkn.models.repository.CategoryRepository;
import api.networkn.services.ICategoryService;
import api.networkn.utils.mappers.ICategoryMapper;

@Service
public class CategoryServiceImp implements ICategoryService {

	private final CategoryRepository categoryRepository;
	private final ICategoryMapper categoryMapper;

	public CategoryServiceImp(final CategoryRepository categoryRepository, final ICategoryMapper categoryMapper) {
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}

	@Override
	public Page<Category> getAll(Pageable page) {
		return categoryRepository.findAll(page);
	}

	@Override
	public CategoryDTO insert(Category category) {
		return categoryMapper.toDto(categoryRepository.save(category));
	}

	@Override
	public Long contarTodos() {
		return categoryRepository.countBy();
	}

	private Category findById(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException());
	}

	@Override
	public CategoryDTO update(CategoryDTO categoryDTO) {
		Category category = findById(categoryDTO.getId());
		return categoryMapper.toDto(categoryRepository.save(montarCategory(categoryDTO, category)));
	}

	private Category montarCategory(CategoryDTO categoryDTO, Category category) {
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());
		return category;
	}

	@Override
	public void delete(Long categoryId) {
		Category categoryById = findById(categoryId);
		categoryRepository.delete(categoryById);
	}

	@Override
	public List<CategoryDTO> getAll() {
		return categoryMapper.toDto(categoryRepository.findAll());
	}

	@Override
	public CategoryDTO findByIdDTO(Long id) {
		Category category = findById(id);
		return categoryMapper.toDto(category);
	}

}
