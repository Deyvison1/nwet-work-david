package api.networkn.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.networkn.models.Product;
import api.networkn.models.dtos.ProductDTO;
import api.networkn.models.repository.IProductRepository;
import api.networkn.services.IProductService;
import api.networkn.utils.mappers.IProductMapper;

@Service
public class ProductServiceImpl implements IProductService {

	private IProductRepository productRepository;
	
	private IProductMapper mapper;
	
	@Autowired
	public ProductServiceImpl(IProductRepository productRepository, IProductMapper mapper) {
		this.productRepository = productRepository;
		this.mapper = mapper;
	}
	
	@Override
	public Product addOrUpdateProduct(Product product) {
		Product productAddOrInsert = (Objects.nonNull(product.getId())? product : mountProduct(product));
		return productRepository.save(productAddOrInsert);
	}
	
	private Product mountProduct(Product product) {
		return Product.builder()
				.category(product.getCategory())
				.name(product.getName())
				.description(product.getDescription())
				.value(product.getValue())
				.id(product.getId())
				.build();
	}
	
	@Override
	public List<ProductDTO> getAll() {
		return mapper.toList(productRepository.findAll());
	}

}
