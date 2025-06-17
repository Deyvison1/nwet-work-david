package api.networkn.services.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api.networkn.exception.NotFoundException;
import api.networkn.exception.UnsupportedFileException;
import api.networkn.file.exporter.contract.FileExporter;
import api.networkn.file.exporter.factory.FileExporterFactory;
import api.networkn.file.imported.contract.FileImported;
import api.networkn.file.imported.factory.FileImportedFactory;
import api.networkn.models.Category;
import api.networkn.models.dtos.CategoryDTO;
import api.networkn.models.repository.CategoryRepository;
import api.networkn.services.ICategoryService;
import api.networkn.utils.mappers.ICategoryMapper;

@Service
public class CategoryServiceImp implements ICategoryService {

	private final CategoryRepository categoryRepository;
	private final ICategoryMapper categoryMapper;
	private final FileImportedFactory importer;
	private final FileExporterFactory exporter;

	public CategoryServiceImp(final CategoryRepository categoryRepository, final ICategoryMapper categoryMapper,
			final FileImportedFactory importer, final FileExporterFactory exporter) {
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
		this.importer = importer;
		this.exporter = exporter;
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

	public List<CategoryDTO> massCreation(MultipartFile file) {
		if (file.isEmpty())
			throw new UnsupportedFileException("Please set a valid file");

		try (InputStream inputStream = file.getInputStream()) {
			String fileName = Optional.ofNullable(file.getOriginalFilename())
					.orElseThrow(() -> new UnsupportedFileException("File name cannot be null"));

			FileImported fileImported = this.importer.getImporter(fileName);

			List<Category> entities = fileImported.importFile(inputStream).stream()
					.map(dto -> categoryRepository.save(categoryMapper.toEntity(dto))).toList();

			return categoryMapper.toDto(entities);
		} catch (Exception e) {
			throw new UnsupportedFileException("Error processing the file");
		}

	}

	public Resource exportPage(Pageable page, String acceptHeader) {
		var listCategoryDTO = categoryRepository.findAll(page).map(category -> categoryMapper.toDto(category))
				.getContent();

		try {
			FileExporter exporter = this.exporter.getImporter(acceptHeader);
			return exporter.exportFile(listCategoryDTO);
		} catch (Exception e) {
			throw new RuntimeException("Error during file export! ", e);
		}
	}

	@Override
	public CategoryDTO findByIdDTO(Long id) {
		Category category = findById(id);
		return categoryMapper.toDto(category);
	}

}
