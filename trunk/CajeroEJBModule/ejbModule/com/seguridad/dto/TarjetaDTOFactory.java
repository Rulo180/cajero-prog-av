package com.seguridad.dto;

import com.cuentas.entities.Tarjeta;

/**
 * Abstract Factory para conversion de Tarjeta a TarjetaDTO.
 * 
 *
 * 
 */
public class TarjetaDTOFactory {

	public static TarjetaDTO getTarjetaDTO(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Retorna un TarjetaDTO populado con toda la informacion posible.
	 * 
	 * @param u
	 * @return
	 */
	public static TarjetaDTO getAllDataFromTarjeta(Tarjeta u) {
		if (u == null) {
			return null;
		}
		TarjetaDTO result = new TarjetaDTO();
		result.setPin(u.getNroTarjeta());
		result.setFechaInicioTarjeta(u.getFechaInicioTarjeta());
		result.setFechaFinTarjeta(u.getFechaVencimientoTarjeta());
		result.setPin(u.getPinTarjeta());
		result.setActivada((u.getFechaInicioTarjeta() == null || u.getFechaInicioTarjeta().before(new java.util.Date()) 
				&& (u.getFechaVencimientoTarjeta() == null || u.getFechaVencimientoTarjeta().after(new java.util.Date()))));
		
		result.setRol(u.getRol().toString());
		
		return result;
	}

}
