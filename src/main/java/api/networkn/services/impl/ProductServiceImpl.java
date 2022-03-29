package api.networkn.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import api.networkn.models.Product;
import api.networkn.models.dtos.ProductDTO;
import api.networkn.models.repository.IProductRepository;
import api.networkn.services.IProductService;
import api.networkn.utils.mappers.IProductMapper;

@Service
public class ProductServiceImpl implements IProductService {

	private IProductRepository productRepository;
	private IProductMapper productMapper;

	@Autowired
	public ProductServiceImpl(IProductRepository productRepository, IProductMapper productMapper) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	@Override
	public Product updateProduct(Long productId) {
		Product product = findById(productId);
		if (Objects.nonNull(product)) {
			Product productAAtualizar = mountProduct(product);
			return productRepository.save(productAAtualizar);
		}
		return null;
	}

	@Override
	public void deleteProduct(Long productId) {
		Product product = findById(productId);
		if (Objects.nonNull(product)) {
			productRepository.delete(product);
		}
	}
	
	@Override
	public Product findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			return product.get();
		}
		return null;
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	private Product mountProduct(Product product) {
		return Product.builder().category(product.getCategory()).name(product.getName())
				.description(product.getDescription()).value(product.getValue()).id(product.getId())
				.speedDownload(product.getSpeedDownload()).speedUpload(product.getSpeedUpload())
				.taxaAdesao(product.getTaxaAdesao()).valueWifi(product.getValueWifi()).build();
	}

	@Override
	public Page<Product> getAll(Pageable page) {
		return productRepository.findAll(page);
	}
	
	@Override
	public List<ProductDTO> getAll() {
		return productMapper.toList(productRepository.findAll());
	}

	@Override
	public Long contarTodos() {
		return productRepository.countBy();
	}

}
