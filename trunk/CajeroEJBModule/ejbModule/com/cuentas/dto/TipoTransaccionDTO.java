package com.cuentas.dto;

import java.io.Serializable;


public class TipoTransaccionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombreTipoTransaccion;
	private String descripcionTipoTransaccion;

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
