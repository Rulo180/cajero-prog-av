package com.cuentas.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Almacena la informacion que se mostrara al realizar una consulta de estado de cuenta.
 * @author Rulo
 *
 */
public class ConsultaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nroCuenta;
	private double saldo;
	private List<TransaccionDTO> transacciones;
	
	//Getters y Setters
	public String getNroCuenta() {
		return nroCuenta;
	}
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	public List<TransaccionDTO> getTransacciones() {
		return transacciones;
	}
	public void setTransacciones(List<TransaccionDTO> transacciones) {
		this.transacciones = transacciones;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	

}
