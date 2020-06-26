package com.cda.turnero.enums;

public enum TurnoClienteEnum {

	CONFIRMADO ("CONFIRMADO"),
	SIN_CONFIRMAR ("SIN_CONFIRMAR"),
	CANCELADO ("CANCELADO"),
	ACTUAL ("ACTUAL"),
	REALIZADO ("REALIZADO");
	
	
	private String name;
	
	TurnoClienteEnum(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
