
package com.cda.turnero.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class SucursalTipoTurno {

		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer sucursalTipoTurno;
		
		@ManyToOne
		@JoinColumn(name = "id_sucursal")
		private Sucursal sucursal;
		
		@ManyToOne
		@JoinColumn(name = "id_especialidad")
		private Especialidad especialidad;

		public Integer getSucursalTipoTurno() {
			return sucursalTipoTurno;
		}

		public void setSucursalTipoTurno(Integer sucursalTipoTurno) {
			this.sucursalTipoTurno = sucursalTipoTurno;
		}

		public Sucursal getSucursal() {
			return sucursal;
		}

		public void setSucursal(Sucursal sucursal) {
			this.sucursal = sucursal;
		}

		public Especialidad getTipoTurno() {
			return especialidad;
		}

		public void setTipoTurno(Especialidad tipoTurno) {
			this.especialidad = tipoTurno;
		}

		
}
