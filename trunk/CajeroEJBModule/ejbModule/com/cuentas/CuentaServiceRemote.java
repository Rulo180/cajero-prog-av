package com.cuentas;

import java.util.Collection;

import javax.ejb.Remote;

import com.cuentas.dto.CuentaDTO;
import com.cuentas.entities.Cuenta;

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
	public abstract double getSaldo(String nroCuenta);
	
	
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
	 * 
	 * @return
	 */
	public Collection<CuentaDTO> listCuentas();
	
	/**
	 * Busca una cuenta segun el numero de cuenta especificado.
	 * @param nroCuenta
	 * @return
	 */
	public Cuenta findByNroCuenta(String nroCuenta);

}
