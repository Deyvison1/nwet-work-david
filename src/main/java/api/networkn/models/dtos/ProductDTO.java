package api.networkn.models.dtos;

import java.math.BigDecimal;

import api.networkn.models.Category;


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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getValueWifi() {
		return valueWifi;
	}

	public void setValueWifi(BigDecimal valueWifi) {
		this.valueWifi = valueWifi;
	}

	public BigDecimal getTaxaAdesao() {
		return taxaAdesao;
	}

	public void setTaxaAdesao(BigDecimal taxaAdesao) {
		this.taxaAdesao = taxaAdesao;
	}

	public Integer getSpeedDownload() {
		return speedDownload;
	}

	public void setSpeedDownload(Integer speedDownload) {
		this.speedDownload = speedDownload;
	}

	public Integer getSpeedUpload() {
		return speedUpload;
	}

	public void setSpeedUpload(Integer speedUpload) {
		this.speedUpload = speedUpload;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
