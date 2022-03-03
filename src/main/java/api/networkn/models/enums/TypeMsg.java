package api.networkn.models.enums;

public enum TypeMsg {

	ADESAO(1, "Adesão"),
	WIFI(2, "Wifi");
	

	private final Integer id;
	private final String descricao;

	private TypeMsg(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao.concat(": ");
	}

	public static TypeMsg parse(Integer id) {
		for (TypeMsg item : TypeMsg.values()) {
			if (item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}
	
	
}
