package api.networkn.builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import api.networkn.models.Product;

public class ProductBuilderTest {

	public static Product montarCreateEntity() {
		return new Product("Product", "Product full", 50, 50, new BigDecimal(50), new BigDecimal(50),
				new BigDecimal(50), CategoryBuilderTest.montarCreateEntity(), LocalDateTime.now(), null);
	}

	public static Product montarUpdateEntity() {
		return new Product("Product", "Product full", 50, 50, new BigDecimal(50), new BigDecimal(50),
				new BigDecimal(50), CategoryBuilderTest.montarUpdateEntity(), LocalDateTime.now(), LocalDateTime.now());
	}

	public static Product montarEntity(Boolean isUpdate) {
		LocalDateTime dtUpdated = (isUpdate) ? LocalDateTime.now() : null;
		return new Product("Product", "Product full", 50, 50, new BigDecimal(50), new BigDecimal(50),
				new BigDecimal(50), CategoryBuilderTest.montarEntity(Boolean.TRUE), LocalDateTime.now(), dtUpdated);
	}

	public static List<Product> montarFindAllEntity(int size) {
		List<Product> listEntity = new ArrayList<Product>();
		for (int i = 0; i < size; i++) {
			listEntity.add(montarEntity(false));
		}
		return listEntity;
	}
}
