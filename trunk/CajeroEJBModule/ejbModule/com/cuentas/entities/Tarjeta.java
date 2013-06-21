package com.cuentas.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Almacena la informacion relacionada a las tarjetas de una cuenta.
 */

	@Entity
public class Tarjeta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nroTarjeta;

	private Date vencimientoTarjeta = new Date();
	private int pinTarjeta;
	private boolean activada;
	
	
	
	//Constructores
	
	public Tarjeta(){
		
	}


	//Getters y Setters

	public Integer getNroTarjeta() {
		return nroTarjeta;
	}


	public void setNroTarjeta(Integer nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	
	public Date getVencimientoTarjeta() {
		return vencimientoTarjeta;
	}



	public void setVencimientoTarjeta(Date vencimientoTarjeta) {
		this.vencimientoTarjeta = vencimientoTarjeta;
	}



	public int getPinTarjeta() {
		return pinTarjeta;
	}



	public void setPinTarjeta(int pinTarjeta) {
		this.pinTarjeta = pinTarjeta;
	}



	public boolean isActivada() {
		return activada;
	}



	public void setActivada(boolean activada) {
		this.activada = activada;
	}
	
}