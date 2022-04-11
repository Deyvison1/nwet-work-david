package api.networkn.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	private Integer speedDownload;
	
	private Integer speedUpload;
	
	private BigDecimal taxaAdesao;
	
	private BigDecimal valueWifi;
	
	private BigDecimal value;

	@ManyToOne
	private Category category;
	
	public Product() {}
	
}
