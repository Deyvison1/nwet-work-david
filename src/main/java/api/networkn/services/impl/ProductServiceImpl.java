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
	public ProductDTO updateProduct(ProductDTO productDTO) {
		Product product = findById(productDTO.getId());
		if (Objects.nonNull(product)) {
			Product productAAtualizar = mountProduct(productDTO);
			return productMapper.productToProductDTO(productRepository.save(productAAtualizar));
		}
		return null;
	}
	
	private Product mountProduct(ProductDTO productDTO) {
		return Product.builder().category(productDTO.getCategory()).name(productDTO.getName())
				.description(productDTO.getDescription()).value(productDTO.getValue()).id(productDTO.getId())
				.speedDownload(productDTO.getSpeedDownload()).speedUpload(productDTO.getSpeedUpload())
				.taxaAdesao(productDTO.getTaxaAdesao()).valueWifi(productDTO.getValueWifi()).build();
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
