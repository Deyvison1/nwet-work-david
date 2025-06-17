package api.networkn.services.impl;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api.networkn.exception.NotFoundException;
import api.networkn.exception.UnsupportedFileException;
import api.networkn.file.imported.contract.FileImported;
import api.networkn.file.imported.factory.FileImportedFactory;
import api.networkn.models.Product;
import api.networkn.models.dtos.ProductDTO;
import api.networkn.models.repository.IProductRepository;
import api.networkn.services.IProductService;
import api.networkn.utils.mappers.IProductMapper;

@Service
public class ProductServiceImpl implements IProductService {

	private IProductRepository productRepository;
	private IProductMapper productMapper;

	public ProductServiceImpl(IProductRepository productRepository, IProductMapper productMapper) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) {
		Product product = findById(productDTO.getId());
		Product productAAtualizar = mountProduct(productDTO, product);
		return productMapper.toDto(productRepository.save(productAAtualizar));
	}

	private Product mountProduct(ProductDTO productDTO, Product product) {
		product.setId(productDTO.getId());
		product.setCategory(productDTO.getCategory());
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setValue(productDTO.getValue());
		product.setSpeedDownload(productDTO.getSpeedDownload());
		product.setSpeedUpload(productDTO.getSpeedUpload());
		product.setTaxaAdesao(productDTO.getTaxaAdesao());
		product.setValueWifi(productDTO.getValueWifi());
		return product;
	}

	@Override
	public void deleteProduct(Long productId) {
		Product product = findById(productId);
		if (Objects.nonNull(product)) {
			productRepository.delete(product);
		}
	}

	private Product findById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new NotFoundException());
	}

	@Override
	public ProductDTO findByIdDTO(Long id) {
		Product product = findById(id);
		return productMapper.toDto(product);
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
		return productMapper.toDto(productRepository.findAll());
	}
	

	@Override
	public Long contarTodos() {
		return productRepository.countBy();
	}

}
