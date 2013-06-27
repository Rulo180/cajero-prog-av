package com.seguridad.dto;

import java.io.Serializable;
import java.util.Date;

public class TarjetaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nroTarjeta;
	private String rol;
	private String pin;
	private Date fechaInicioTarjeta;
	private Date fechaFinTarjeta;
	private boolean activada;
	
	//Getters y Setters
	public String getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public Date getFechaInicioTarjeta() {
		return fechaInicioTarjeta;
	}
	public void setFechaInicioTarjeta(Date fechaInicioTarjeta) {
		this.fechaInicioTarjeta = fechaInicioTarjeta;
	}
	public Date getFechaFinTarjeta() {
		return fechaFinTarjeta;
	}
	public void setFechaFinTarjeta(Date fechaFinTarjeta) {
		this.fechaFinTarjeta = fechaFinTarjeta;
	}
	public boolean isActivada() {
		return activada;
	}
	public void setActivada(boolean activada) {
		this.activada = activada;
	}	
}