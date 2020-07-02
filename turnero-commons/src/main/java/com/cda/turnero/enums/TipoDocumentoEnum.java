package com.cda.turnero.enums;

public enum TipoDocumentoEnum {

	
	DNI ("DNI"),
	CI ("CI"),
	LE ("LE"),
	LC ("LC");
	
	private String name;
	
	TipoDocumentoEnum(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
