package api.networkn.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import api.networkn.builder.CategoryBuilderTest;
import api.networkn.models.Category;
import api.networkn.models.repository.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository repository;

	@DisplayName("Test created category")
	@Test
	void testCreateCategory() {
		Category entity = CategoryBuilderTest.montarCreateEntity();
		Category savedEntity = this.repository.save(entity);
		assertNotNull(savedEntity);
		assertTrue(savedEntity.getId() > 0);
	}

	@DisplayName("Test update category")
	@Test
	void testUpdateCategory() {
		Category entity = CategoryBuilderTest.montarUpdateEntity();
		Category savedEntity = this.repository.save(entity);
		assertNotNull(savedEntity);
		assertTrue(savedEntity.getUpdatedAt() != null);
	}

	@DisplayName("Test find all category")
	@Test
	void testFindAll() {
		List<Category> listCategory = CategoryBuilderTest.montarFindAllEntity(2);
		this.repository.saveAll(listCategory);
		List<Category> savedEntities = this.repository.findAll();
		assertNotNull(savedEntities);
		assertEquals(2, savedEntities.size());
	}

	@DisplayName("Test find all page category")
	@Test
	void testFindAllPage() {
		List<Category> listCategory = CategoryBuilderTest.montarFindAllEntity(2);
		Pageable pageable = PageRequest.of(0, 20);
		this.repository.saveAll(listCategory);
		Page<Category> savedEntitiesPage = this.repository.findAll(pageable);
		assertNotNull(savedEntitiesPage);
		assertTrue(savedEntitiesPage.getSize() > 0);
	}

	@DisplayName("Test count by category")
	@Test
	void testCountBy() {
		List<Category> listCategory = CategoryBuilderTest.montarFindAllEntity(2);
		this.repository.saveAll(listCategory);
		Long count = this.repository.count();
		assertEquals(2, count);
	}
}
