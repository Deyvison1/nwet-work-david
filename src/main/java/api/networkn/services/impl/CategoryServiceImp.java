package api.networkn.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
		if (Objects.isNull(category)) {
			return null;
		}
		return categoryMapper.toDto(categoryRepository.save(category));
	}
	
	@Override
	public Long contarTodos() {
		return categoryRepository.countBy();
	}

	@Override
	public CategoryDTO update(CategoryDTO categoryDTO) {
		Category categoryById = categoryRepository.findById(categoryDTO.getId()).orElseThrow();
		if (Objects.isNull(categoryById)) {
			return null;
		}
		return categoryMapper.toDto(categoryRepository.save(montarCategory(categoryDTO)));
	}

	private Category montarCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setDescription(categoryDTO.getDescription());
		return category;
	}

	@Override
	public void delete(Long categoryId) {
		Category categoryById = categoryRepository.findById(categoryId).orElseThrow();
		if(Objects.isNull(categoryById)) {
			
		}
		categoryRepository.delete(categoryById);
	}

	@Override
	public List<CategoryDTO> getAll() {
		return categoryMapper.toDto(categoryRepository.findAll());
	}

}
