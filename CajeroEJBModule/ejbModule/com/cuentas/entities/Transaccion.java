package com.cuentas.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*
 * Almacena la informacion relacionada con cada transaccion que se realiza sobre una cuenta.
 */

@Entity
public class Transaccion {
	
	@Id
	private Integer transaccionId;
	
	private Date fechaTransaccion = new Date();
	private double montoTransaccion;
	
	@ManyToOne
	private TipoTransaccion tipoTransaccion;
	
	
	//Constructores
	
	public Transaccion(){
		
	}


	//Getters y Setters
	
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


	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}


	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	
}
