package com.seguridad;

import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.seguridad.dto.TarjetaDTO;

@Remote
public interface SeguridadServiceRemote {
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public abstract TarjetaDTO login(String nroTarjeta);
}