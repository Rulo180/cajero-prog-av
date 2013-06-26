package com.cuentas.dto;


import com.cuentas.entities.Cuenta;

public class CuentaDTOFactory {

	/**
	 * Devuelve un DTO de cuenta con toda la informacion proporcionada
	 * @param cuenta
	 * @return
	 */
	public static CuentaDTO getCuentaDTO(Cuenta cuenta) {
		if (cuenta == null){
			return null;
		}
		CuentaDTO dto = new CuentaDTO();
		dto.setNroCuenta(cuenta.getNroCuenta());
		dto.setSaldoCuenta(cuenta.getSaldoCuenta());
		dto.setTitularCuenta(cuenta.getTitularCuenta());
		
		return dto;
	}


}
