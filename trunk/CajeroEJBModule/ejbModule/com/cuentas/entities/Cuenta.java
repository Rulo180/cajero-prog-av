package com.cuentas.entities;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


/**
 * Almacena la informacion referida a las cuentas de los clientes.
 * @author Rulo
 *
 */
	@Entity
public class Cuenta implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private String nroCuenta;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, orphanRemoval = true)
	@JoinColumn(name = "cuentaId")
	private List<Tarjeta> tarjetas;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, orphanRemoval = true)
	@JoinColumn(name = "cuentaId")
	private List<Transaccion> transacciones;
	
	private double saldoCuenta;
	private String titularCuenta;
	private Date fechaCreacion = new Date();
	
	
	//Constructores
	
	public Cuenta(){
		
	}


	//Getters y Setters
	
	public String getNroCuenta() {
		return nroCuenta;
	}


	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}


	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}


	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}


	public List<Transaccion> getTransacciones() {
		return transacciones;
	}


	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}


	public double getSaldoCuenta() {
		return saldoCuenta;
	}


	public void setSaldoCuenta(double saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}


	public String getTitularCuenta() {
		return titularCuenta;
	}


	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
