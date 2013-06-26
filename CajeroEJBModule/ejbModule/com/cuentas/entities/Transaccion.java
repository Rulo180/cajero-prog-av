package com.cuentas.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Almacena la informacion relacionada con cada transaccion que se realiza sobre una cuenta.
 */

@Entity
public class Transaccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transaccionId;
	
	private Date fechaTransaccion = new Date();
	private double montoTransaccion;
	
	@Enumerated(EnumType.STRING)
	private com.cuentas.dto.TipoTransaccion tipoTransaccion;
	
	
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


	public com.cuentas.dto.TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}


	public void setTipoTransaccion(com.cuentas.dto.TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	
}
