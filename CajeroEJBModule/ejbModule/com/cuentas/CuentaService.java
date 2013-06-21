package com.cuentas;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.cuentas.dto.CuentaDTO;
import com.cuentas.dto.CuentaDTOFactory;
import com.cuentas.entities.Cuenta;
import com.cuentas.repository.CuentaRepository;

/**
 * Session Bean implementation class CuentaService
 */
@Stateless
@LocalBean
public class CuentaService implements CuentaServiceRemote{
	
	@EJB
	private CuentaRepository cuentaRepository;
	
	@EJB
	private CuentaServiceValidation validator;
	
    /**
     * Default constructor. 
     */
    public CuentaService() {
        
    }

	@Override
	public double getSaldo(String nroCuenta) throws BusinessException {
		List<ValidationError> errors = validator.validarCuenta(nroCuenta);
		if (errors.size() > 0) {
			throw new BusinessException("Errores al cargar la cuenta.", errors);
		} 
		return cuentaRepository.get(nroCuenta).getSaldoCuenta();
	}

	@Override
	public void depositar(String nroCuenta, double monto) throws BusinessException {
		List<ValidationError> errors = validator.validarDeposito(nroCuenta, monto);
		if (errors.size() > 0) {
			throw new BusinessException("Errores al realizar el deposito.", errors);
		} 
		
		Cuenta cuenta = cuentaRepository.get(nroCuenta);
		cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() + monto);
	}

	@Override
	public void retirar(String nroCuenta, double monto) throws BusinessException{
		List<ValidationError> errors = validator.validarRetiro(nroCuenta, monto);
		if (errors.size() > 0) {
			throw new BusinessException("Errores al realizar el retiro.", errors);
		} 
		
		Cuenta cuenta = cuentaRepository.get(nroCuenta);
		cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() - monto);
	}

	@Override
	public void transferir(String nroCuenta, String cuentaDestino, double monto) throws BusinessException{
		List<ValidationError> errors = validator.validarTransferencia(nroCuenta, cuentaDestino, monto);
		if (errors.size() > 0) {
			throw new BusinessException("Errores al realizar el transferencia.", errors);
		} 
		
		Cuenta cuenta = cuentaRepository.get(nroCuenta);
		Cuenta destino = cuentaRepository.get(cuentaDestino);
		
		cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() - monto);
		destino.setSaldoCuenta(destino.getSaldoCuenta() + monto);
		
	}

	@Override
	public Collection<CuentaDTO> listCuentas() {
		return CuentaDTOFactory.getCuentaDTO(cuentaRepository.listAll());
	}

	@Override
	public CuentaDTO findByNroCuenta(String nroCuenta) {
		return CuentaDTOFactory.getCuentaDTO(cuentaRepository.get(nroCuenta));
	}
    
	

}
