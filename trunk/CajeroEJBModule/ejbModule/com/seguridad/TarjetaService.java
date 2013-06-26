package com.seguridad;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.cuentas.entities.Tarjeta;
import com.seguridad.dto.TarjetaDTO;
import com.seguridad.dto.TarjetaDTOFactory;
import com.seguridad.repository.TarjetaRepository;

/**
 * Session Bean implementation class TarjetaService
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TarjetaService implements TarjetaServiceRemote{
	
	@EJB
	private TarjetaRepository tarjetaRepository;

	@EJB
	private TarjetaServiceValidations tarjetaServiceValidations;


    /**
     * Default constructor. 
     */
    public TarjetaService() {
        // TODO Auto-generated constructor stub
    }

    
    /**
     * 
     */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public TarjetaDTO findByNroTarjeta(String nroTarjeta) {
		List<ValidationError> errors = tarjetaServiceValidations.validarTarjeta(nroTarjeta);
		if (errors.size() > 0) {
			throw new BusinessException("Tarjeta no se encuentra en la DB.", errors);
		}
		
		return TarjetaDTOFactory.getTarjetaDTO(tarjetaRepository.get(nroTarjeta));
	}

	/**
	 * 
	 */
	@Override
	public void actualizarPin(String nroTarjeta, String pinAnterior, String pinNuevo) throws BusinessException {
		List<ValidationError> errors = tarjetaServiceValidations.validarCambioPin(nroTarjeta, pinAnterior, pinNuevo);
		if (errors.size() > 0) {
			throw new BusinessException("Datos de usuario invalidos.", errors);
		}
		
		Tarjeta tarjeta = tarjetaRepository.get(nroTarjeta);
		tarjeta.setPinTarjeta(pinNuevo);
	}
    
    

}
