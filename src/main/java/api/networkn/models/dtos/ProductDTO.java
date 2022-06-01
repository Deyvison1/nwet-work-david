package api.networkn.models.dtos;

import java.math.BigDecimal;

import api.networkn.models.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductDTO {

	private Long id;
	
	private String name;
	
	private String description;
	
	private BigDecimal valueWifi;
	
	private BigDecimal taxaAdesao;
	
	private Integer speedDownload;
	
	private Integer speedUpload;
	
	private BigDecimal value;
	
	private Category category;
	
}
