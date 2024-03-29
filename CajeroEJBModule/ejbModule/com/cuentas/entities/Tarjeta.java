package com.cuentas.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.seguridad.dto.RolSeguridad;


/**
 * Tarjetas del sistema, esta entidad es utilizada por los mecanismos de
 * seguridad para realizar el login en el sistema.
 * 
 * Cada tarjeta posee una lista de roles del sistema que tiene habilitados.
 */
	@Entity
public class Tarjeta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty()
	@Size(max = 128)
	private String nroTarjeta;
	
	@NotEmpty()
	@Size(max = 4)
	private String pinTarjeta;
	
	private boolean activada;
	
	@NotNull()
	@Temporal(TemporalType.DATE)
	private Date fechaInicioTarjeta = new Date();
	
	@NotNull()
	@Temporal(TemporalType.DATE)
	private Date fechaVencimientoTarjeta = new Date();
	
@Enumerated(EnumType.STRING)
	private RolSeguridad rol;

	
	
	//Getters y Setters

	public String getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	
	public Date getFechaVencimientoTarjeta() {
		return fechaVencimientoTarjeta;
	}
	public void setFechaVencimientoTarjeta(Date fechaVencimientoTarjeta) {
		this.fechaVencimientoTarjeta = fechaVencimientoTarjeta;
	}

	public String getPinTarjeta() {
		return pinTarjeta;
	}
	public void setPinTarjeta(String pinTarjeta) {
		this.pinTarjeta = pinTarjeta;
	}

	public boolean isActivada() {
		return activada;
	}
	public void setActivada(boolean activada) {
		this.activada = activada;
	}

	public Date getFechaInicioTarjeta() {
		return fechaInicioTarjeta;
	}
	public void setFechaInicioTarjeta(Date fechaInicioTarjeta) {
		this.fechaInicioTarjeta = fechaInicioTarjeta;
	}	
	
	public RolSeguridad getRol() {
		return rol;
	}

	public void setRol(RolSeguridad rol) {
		this.rol = rol;
	}
}