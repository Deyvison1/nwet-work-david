package api.networkn.models.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryDTO {

	private Long id;
	
	private String name;
	
	private String description;
	
}
