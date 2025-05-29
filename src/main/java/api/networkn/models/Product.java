package api.networkn.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table
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

	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	@Column(name = "updated_at", nullable = true, updatable = true)
	private LocalDateTime updatedAt;

	public Product() {
	}

	public Product(Long id, String name, String description, Integer speedDownload, Integer speedUpload,
			BigDecimal taxaAdesao, BigDecimal valueWifi, BigDecimal value, Category category, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.speedDownload = speedDownload;
		this.speedUpload = speedUpload;
		this.taxaAdesao = taxaAdesao;
		this.valueWifi = valueWifi;
		this.value = value;
		this.category = category;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Product(String name, String description, Integer speedDownload, Integer speedUpload,
			BigDecimal taxaAdesao, BigDecimal valueWifi, BigDecimal value, Category category, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.name = name;
		this.description = description;
		this.speedDownload = speedDownload;
		this.speedUpload = speedUpload;
		this.taxaAdesao = taxaAdesao;
		this.valueWifi = valueWifi;
		this.value = value;
		this.category = category;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

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

	public BigDecimal getTaxaAdesao() {
		return taxaAdesao;
	}

	public void setTaxaAdesao(BigDecimal taxaAdesao) {
		this.taxaAdesao = taxaAdesao;
	}

	public BigDecimal getValueWifi() {
		return valueWifi;
	}

	public void setValueWifi(BigDecimal valueWifi) {
		this.valueWifi = valueWifi;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
