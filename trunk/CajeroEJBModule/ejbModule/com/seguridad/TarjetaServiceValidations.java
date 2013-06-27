package com.seguridad;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.cuentas.entities.Tarjeta;
import com.seguridad.repository.TarjetaRepository;
/**
 * Esta clase implementa todas las validaciones necesarias relacionadas con TarjetaService.
 * 
 *
 */
@Stateless
@LocalBean
public class TarjetaServiceValidations {
	
	@EJB
	private TarjetaRepository tarjetaRepository;
	
	/**
	 * Valida el cambio de contraseña.
	 * 
	 * @param login
	 * @param passwordViejo
	 * @param passwordNuevo
	 */
	public List<ValidationError> validarCambioPin(String nroTarjeta, String pinAnterior, String pinNuevo) {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		Tarjeta tarj = tarjetaRepository.get(nroTarjeta);
		if (tarj == null) {
			errors.add(new ValidationError("nroTarjeta", "La tarjeta no existe en la base de datos"));
		}
		
		if (pinAnterior.equals(tarj.getPinTarjeta())) {
			errors.add(new ValidationError("pinAnterior", "El pin actual es incorrecto."));
		}			
		return errors;
		}
	
	/**
	 * Valida la activacion de una tarjeta.
	 * 
	 * @param nroTarjeta
	 */
	public List<ValidationError> validarActivarTarjeta(String nroTarjeta) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		Tarjeta tarj = tarjetaRepository.get(nroTarjeta);
		if (tarj == null) {
			errors.add(new ValidationError("nroTarjeta", "La tarjeta no existe en la base de datos."));
		}
		return errors;
	}
	
	/**
	 * Valida la desactivacion de una tarjeta.
	 * 
	 * @param nroTarjeta
	 */
	public List<ValidationError> validarDesactivarTarjeta(String nroTarjeta) {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		Tarjeta tarj = tarjetaRepository.get(nroTarjeta);
		if (tarj == null) {
			errors.add(new ValidationError("nroTarjeta", "La tarjeta no existe en la base de datos."));
		}
		return errors;

	}
}
