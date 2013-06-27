package com.cuentas.dto;

import java.io.Serializable;
import java.util.Date;


public class TransaccionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer transaccionId;
	
	private Date fechaTransaccion = new Date();
	private double montoTransaccion;
	
	private com.cuentas.dto.TipoTransaccion tipoTransaccion;

	public Integer getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public double getMontoTransaccion() {
		return montoTransaccion;
	}

	public void setMontoTransaccion(double montoTransaccion) {
		this.montoTransaccion = montoTransaccion;
	}

	public com.cuentas.dto.TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(com.cuentas.dto.TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	
}
