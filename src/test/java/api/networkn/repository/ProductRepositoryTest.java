package api.networkn.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import api.networkn.builder.CategoryBuilderTest;
import api.networkn.builder.ProductBuilderTest;
import api.networkn.models.Category;
import api.networkn.models.Product;
import api.networkn.models.repository.CategoryRepository;
import api.networkn.models.repository.IProductRepository;

@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private IProductRepository repository;
	
	@Autowired
	private CategoryRepository repositoryCategory;
	
	
	@DisplayName("Test created product")
	@Test
	void testCreatedProduct() {
		Product entity = ProductBuilderTest.montarCreateEntity();
		Category category = CategoryBuilderTest.montarCreateEntity();
		repositoryCategory.save(category);
		Product savedEntity = this.repository.save(entity);
		assertNotNull(savedEntity);
		assertNotNull(savedEntity.getId());
	}
}
