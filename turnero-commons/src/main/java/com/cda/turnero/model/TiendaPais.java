package com.cda.turnero.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TiendaPais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tiendaPaisId;
	
	@ManyToOne
	@JoinColumn(name = "id_tienda")
	private Tienda tienda;
	
	@ManyToOne
	@JoinColumn(name = "id_pais")
	private Pais pais;

	public Integer getTiendaPaisId() {
		return tiendaPaisId;
	}

	public void setTiendaPaisId(Integer tiendaPaisId) {
		this.tiendaPaisId = tiendaPaisId;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
