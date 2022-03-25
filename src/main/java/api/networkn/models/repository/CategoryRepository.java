package api.networkn.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import api.networkn.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Page<Category> findAll(Pageable page);
	
	Long countBy();
}
