package api.networkn.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import api.networkn.models.Product;
import api.networkn.models.dtos.ProductDTO;

@Service
public interface IProductService {
	
	Product addProduct(Product product);
	
	Product updateProduct(Long product);
	
	Page<Product> getAll(Pageable page);
	
	List<ProductDTO> getAll();
	
	void deleteProduct(Long id);
		
	Product findById(Long id);
	
	Long contarTodos();
}
