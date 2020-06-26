package com.cda.turnero.enums;

public enum EstadoTurno {	 
	
	DISPONIBLE ("DISPONIBLE"),
	COMPLETO ("COMPLETO");
	
	private String name;
	
	EstadoTurno(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
