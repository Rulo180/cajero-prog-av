package com.cuentas;

import javax.ejb.Remote;

import com.cuentas.dto.ConsultaDTO;
import com.cuentas.dto.CuentaDTO;

/**
 * Define la interfaz para la CuentaService
 * @author Rulo
 *
 */

@Remote
public interface CuentaServiceRemote {
	
	
	/**
	 * Devuelve el saldo remanente de una cuenta
	 * @return
	 */
	public abstract ConsultaDTO consultar(String nroCuenta);
	
	
	/**
	 * Aumenta el saldo de una cuenta en un determinado monto.
	 * @param nroCuenta
	 * @param monto
	 */
	public void depositar(String nroCuenta, double monto);
	
	/**
	 * Disminuye el saldo de una cuenta en un determinado monto.
	 * @param nroCuenta
	 * @param monto
	 */
	public void retirar(String nroCuenta, double monto);
	
	/*
	 * Transfiere un % del saldo de una cuenta a otra.
	 */
	public void transferir(String nroCuenta, String cuentaDestino, double monto);
	
	
	/**
	 * Busca una cuenta segun el numero de cuenta especificado.
	 * @param nroCuenta
	 * @return
	 */
	public CuentaDTO findByNroCuenta(String nroCuenta);
	
	/**
	 * Busca la cuenta que tiene asociada una tarjeta con el numero ingresado.
	 * @param nroTarjeta
	 * @return
	 */
	public CuentaDTO findByNroTarjeta(String nroTarjeta);

}
