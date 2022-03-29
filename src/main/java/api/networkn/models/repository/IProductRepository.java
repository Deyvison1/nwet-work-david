package api.networkn.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import api.networkn.models.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findAll(Pageable page);
	
	Long countBy();
}
