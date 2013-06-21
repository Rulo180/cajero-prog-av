package com.cuentas.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoTransaccion {

	@Id
	private String nombreTipoTransaccion;

	private String descripcionTipoTransaccion;
	
	//Constructores
	
	public TipoTransaccion(){
		
	}
	
	//Getters y Setters
	
	public String getNombreTipoTransaccion() {
		return nombreTipoTransaccion;
	}

	public void setNombreTipoTransaccion(String nombreTipoTransaccion) {
		this.nombreTipoTransaccion = nombreTipoTransaccion;
	}

	public String getDescripcionTipoTransaccion() {
		return descripcionTipoTransaccion;
	}

	public void setDescripcionTipoTransaccion(String descripcionTipoTransaccion) {
		this.descripcionTipoTransaccion = descripcionTipoTransaccion;
	}
	
	
}
