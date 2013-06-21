package com.cuentas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.cuentas.entities.Cuenta;
import com.cuentas.repository.CuentaRepository;
import com.application.exceptions.*;


@LocalBean
@Stateless
public class CuentaServiceValidation {

	@EJB
	private CuentaRepository cuentaRepository;
	
	@EJB
	private CuentaServiceValidation validador;
	
	private final static int MAX_DEPOSITO = 50000;
	private final static int MAX_TRANSFERENCIA = 20000;
	
	/**
	 * Valida la existencia de una cuenta en la BD.
	 * @param nroCuenta
	 * @return
	 */
	public List<ValidationError> validarCuenta(String nroCuenta){
		List<ValidationError> errores = new ArrayList<ValidationError>();
		
		Cuenta cuenta = cuentaRepository.get(nroCuenta);
		
		if (cuenta == null){
			errores.add(new ValidationError("nroCuenta", "La cuenta especificada no se encuentra en la BD."));
		}
		return errores;
	}
	
	/**
	 * Valida los requisitos para realizar un retiro.
	 * @param nroCuenta
	 * @param monto
	 * @return
	 */
	public List<ValidationError> validarRetiro(String nroCuenta, double monto){
		List<ValidationError> errores = new ArrayList<ValidationError>();
		
		Cuenta cuenta = cuentaRepository.get(nroCuenta);
		
		if (cuenta == null){
			errores.add(new ValidationError("nroCuenta", "La cuenta especificada no existe en la BD."));
		}
		if(monto <= cuenta.getSaldoCuenta()){
			errores.add(new ValidationError("monto", "El monto de retiro ingresado es mayor al saldo disponible."));
		}
		return errores;		
		
	}
	
	/**
	 * Valida los requisitos para realizar un deposito.
	 * @param nroCuenta
	 * @param monto
	 * @return
	 */
	public List<ValidationError> validarDeposito(String nroCuenta, double monto){
		List<ValidationError> errores = new ArrayList<ValidationError>();
		
		Cuenta cuenta = cuentaRepository.get(nroCuenta);
		
		if (cuenta == null){
			errores.add(new ValidationError("nroCuenta", "La cuenta especificada no existe en la BD."));
		}
		if (0 < monto && monto <= MAX_DEPOSITO){
			errores.add(new ValidationError("monto", "El monto de deposito debe ser mayor a 0 y menor a" + MAX_DEPOSITO));
		}
		return errores;
	}
	
	/**
	 * Valida los requisitos para realizar una transferencia.
	 * @param nroCuenta
	 * @param destino
	 * @param monto
	 * @return
	 */
	public List<ValidationError> validarTransferencia(String nroCuenta, String destino, double monto){
		List<ValidationError> errores = new ArrayList<ValidationError>();

		Cuenta cuenta = cuentaRepository.get(nroCuenta);
		Cuenta cuentaDestino = cuentaRepository.get(destino);
		
		if (cuenta == null){
			errores.add(new ValidationError("nroCuenta", "La cuenta especificada no existe en la BD."));
		}
		if (cuentaDestino == null){
			errores.add(new ValidationError("nroCuenta", "La cuenta destino especificada no existe en la BD."));
		}
		if (0 < monto && monto <= MAX_TRANSFERENCIA){
			errores.add(new ValidationError("monto", "El monto de transferencia debe ser mayor a 0 y menor a" + MAX_TRANSFERENCIA));
		}
		if (monto > cuenta.getSaldoCuenta()){
			errores.add(new ValidationError("monto", "El monto de transferencia es mayor al saldo disponible en la cuenta origen."));
		}
		return errores;
	}
}
