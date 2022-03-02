package api.networkn.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.networkn.models.Product;
import api.networkn.models.dtos.ProductDTO;
import api.networkn.services.IProductService;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAll() {
		return ResponseEntity.ok(productService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<Product> addOrInsert(@RequestBody Product product) {
		return ResponseEntity.ok(productService.addOrUpdateProduct(product));
	}
	
}
