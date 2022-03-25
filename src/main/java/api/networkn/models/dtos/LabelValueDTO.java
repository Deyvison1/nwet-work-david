package api.networkn.models.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
public class LabelValueDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Long value;

	private final String label;

}
