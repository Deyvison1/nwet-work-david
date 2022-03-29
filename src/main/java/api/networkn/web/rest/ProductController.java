package api.networkn.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.networkn.models.Product;
import api.networkn.models.dtos.ProductDTO;
import api.networkn.services.IProductService;

@CrossOrigin(origins = "*", exposedHeaders = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll(Pageable pageable) {
		Page<Product> listProduct = productService.getAll(pageable);
		final Long total = productService.contarTodos();

		HttpHeaders headers = new HttpHeaders();
		headers.add("X_TOTAL_COUNT", String.valueOf(total));
		return new ResponseEntity<List<Product>>(listProduct.getContent(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<ProductDTO>> getAll() {
		return ResponseEntity.ok(productService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<Product> add(@RequestBody Product product) {
		return ResponseEntity.ok(productService.addProduct(product));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Product> update(@PathVariable Long id) {
		return ResponseEntity.ok(productService.updateProduct(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
	
}
