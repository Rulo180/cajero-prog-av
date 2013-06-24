package com.seguridad;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.application.exceptions.BusinessException;
import com.seguridad.dto.TarjetaDTO;



@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SeguridadService implements SeguridadServiceRemote {
	@EJB
	private TarjetaService tarjetaService;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public TarjetaDTO login(String nroTarjeta) throws BusinessException{
		TarjetaDTO tarj = tarjetaService.findByNroTarjeta(nroTarjeta);
		if (tarj == null) {
			throw new BusinessException("Tarjeta no Existe.");
		}
		if(tarj.isActivada() == false){
			throw new BusinessException("Tarjeta Inactiva.");
		}
		if(tarj.getFechaInicioTarjeta() != null && tarj.getFechaInicioTarjeta().after(new java.util.Date())) {
			throw new BusinessException("El usuario no esta vigente.");
		}
		if(tarj.getFechaFinTarjeta() != null && tarj.getFechaFinTarjeta().before(new java.util.Date())) {
			throw new BusinessException("La tarjeta no esta vigente.");
		}
		return tarj;
	}
}
