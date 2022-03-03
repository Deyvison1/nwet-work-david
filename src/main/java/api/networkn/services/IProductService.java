package api.networkn.services;

import java.util.List;

import org.springframework.stereotype.Service;

import api.networkn.models.Product;
import api.networkn.models.dtos.ProductDTO;

@Service
public interface IProductService {
	
	Product addProduct(Product product);
	
	Product updateProduct(Long product);
	
	List<ProductDTO> getAll();
	
	void deleteProduct(Long id);
		
}
