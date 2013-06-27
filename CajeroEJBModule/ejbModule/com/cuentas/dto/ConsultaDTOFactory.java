package com.cuentas.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cuentas.entities.Transaccion;

public class ConsultaDTOFactory {
	
	
	
	public static ConsultaDTO getConsultaDTO(String nroCuenta, double saldo, Collection<Transaccion> transacciones){
		
		if (nroCuenta == null){
			return null;
		}
		
		ConsultaDTO dtoResult = new ConsultaDTO();
		dtoResult.setNroCuenta(nroCuenta);
		dtoResult.setSaldo(saldo);
		List<TransaccionDTO> tmpTransacciones = new ArrayList<TransaccionDTO>();
		
		//Llena la lista de TransaccionDTO
		for (Transaccion tran : transacciones){
			TransaccionDTO dto = new TransaccionDTO();
			dto.setTransaccionId(tran.getTransaccionId());
			dto.setMontoTransaccion(tran.getMontoTransaccion());
			dto.setFechaTransaccion(tran.getFechaTransaccion());
			dto.setTipoTransaccion(tran.getTipoTransaccion());
			tmpTransacciones.add(dto);
		}
		
		dtoResult.setTransacciones(tmpTransacciones);
		
		return dtoResult;
	}

}
