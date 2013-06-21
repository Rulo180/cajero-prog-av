package com.cuentas.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	/**
	 * Devuelve una lista de DTO de cuentas con la informacion de la lista de cuentas.
	 * @param cuentas
	 * @return
	 */
	public static Collection<CuentaDTO> getCuentaDTO(Collection<Cuenta> cuentas) {
		if (cuentas == null){
			return null;
		}
		
		List<CuentaDTO> dtoList = new ArrayList<CuentaDTO>();
		
		for (Cuenta cuenta : cuentas){
			dtoList.add(getCuentaDTO(cuenta));
		}
		
		return dtoList;
	}

}
