package api.networkn.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import api.networkn.models.Category;

public class CategoryBuilderTest {

	public static Category montarCreateEntity() {
		return new Category("Full", "A mais top", LocalDateTime.now(), null);
	}
	
	public static Category montarUpdateEntity() {
		return new Category( "Full", "A mais top", LocalDateTime.now(), LocalDateTime.now());
	}
	
	public static Category montarEntity(Boolean isUpdate) {
		LocalDateTime dtUpdated = (isUpdate)? LocalDateTime.now() : null;
		return new Category("Full", "A mais top", LocalDateTime.now(), dtUpdated);
	}
	
	public static List<Category> montarFindAllEntity(int size) {
		List<Category> listEntity = new ArrayList<Category>();
		for(int i = 0; i < size; i++) {
			listEntity.add(montarEntity(false));
		}
		return listEntity;
	}
}
