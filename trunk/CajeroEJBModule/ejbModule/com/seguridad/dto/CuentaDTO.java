package com.seguridad.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CuentaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nroCuenta;
	private List<String> roles;
	private String titularCuenta;
	private Double saldoCuenta;
	private Date fechaCreacionCuenta;
	
	
	public String getNroCuenta() {
		return nroCuenta;
	}
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public String getTitularCuenta() {
		return titularCuenta;
	}
	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}
	
	public Double getSaldoCuenta() {
		return saldoCuenta;
	}
	public void setSaldoCuenta(Double saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}
	
	public Date getFechaCreacionCuenta() {
		return fechaCreacionCuenta;
	}
	public void setFechaCreacionCuenta(Date fechaCreacionCuenta) {
		this.fechaCreacionCuenta = fechaCreacionCuenta;
	}
}