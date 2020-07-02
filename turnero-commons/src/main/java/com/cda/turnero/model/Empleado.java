package com.cda.turnero.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="empleado")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona {

	
	
}
