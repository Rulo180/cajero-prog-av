package com.seguridad;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.application.exceptions.BusinessException;

import com.seguridad.dto.TarjetaDTO;


public interface TarjetaServiceRemote {
	
	/**
	 * 
	 * @param nroTarjeta
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public abstract TarjetaDTO findByNroTarjeta(String nroTarjeta);

	/**
	 * Permite modificar el pin de la tarjeta
	 * @param pin
	 * @param pinAnterior
	 * @param pinNuevo
	 * @throws BusinessException
	 * @throws BusinessConstraintViolationException
	 */
	void actualizarPin(String pin, String pinAnterior, String pinNuevo) throws BusinessException;
	

}
