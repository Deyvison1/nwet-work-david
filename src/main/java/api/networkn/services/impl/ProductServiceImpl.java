package api.networkn.services.impl;

import java.util.List;
import java.util.Optional;

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
	public Product updateProduct(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) {
			Product productAAtualizar = mountProduct(product.get());
			return productRepository.save(productAAtualizar);
		}
		return null;
	}

	@Override
	public void deleteProduct(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) {
			productRepository.delete(product.get());
		}
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
	public List<ProductDTO> getAll() {
		return mapper.toList(productRepository.findAll());
	}

}
