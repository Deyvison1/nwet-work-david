package api.networkn.utils.mappers;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;

import api.networkn.models.Product;
import api.networkn.models.dtos.ProductDTO;

@Mapper(componentModel = "spring")
public interface IProductMapper {

	ProductDTO productToProductDTO(Product parcela);

	List<ProductDTO> toList(Collection<Product> parcelas);

}
