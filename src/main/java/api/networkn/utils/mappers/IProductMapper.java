package api.networkn.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import api.networkn.models.Product;
import api.networkn.models.dtos.ProductDTO;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper extends BaseEntityMapper<Product, ProductDTO> {
}
