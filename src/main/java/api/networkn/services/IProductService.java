package api.networkn.services;

import java.util.List;

import org.springframework.stereotype.Service;

import api.networkn.models.Product;
import api.networkn.models.dtos.ProductDTO;

@Service
public interface IProductService {
	
	Product addOrUpdateProduct(Product product);
	
	List<ProductDTO> getAll();
		
}
