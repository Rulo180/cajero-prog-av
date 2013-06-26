package com.cuentas;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.cuentas.dto.CuentaDTO;
import com.cuentas.dto.CuentaDTOFactory;
import com.cuentas.dto.TipoTransaccionDTO;
import com.cuentas.entities.Cuenta;
import com.cuentas.entities.TipoTransaccion;
import com.cuentas.entities.Transaccion;
import com.cuentas.repository.CuentaRepository;

/**
 * Session Bean implementation class CuentaService
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
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
		Transaccion transaccion = new Transaccion();
		List<Transaccion> transacciones = cuenta.getTransacciones();
		
		
		TipoTransaccionDTO tipoTransaccionDto = TipoTransaccionService.findById("Deposito");
		TipoTransaccion tipoTransaccion = new TipoTransaccion();
		tipoTransaccion.setNombreTipoTransaccion(tipoTransaccionDto.getNombreTipoTransaccion());
		tipoTransaccion.setDescripcionTipoTransaccion(tipoTransaccionDto.getDescripcionTipoTransaccion());
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setFechaTransaccion(new Date());
		transaccion.setMontoTransaccion(monto);
		transacciones.add(transaccion);
		
		cuenta.setTransacciones(transacciones);
		cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() + monto);
	}

	@Override
	public void retirar(String nroCuenta, double monto) throws BusinessException{
		List<ValidationError> errors = validator.validarRetiro(nroCuenta, monto);
		if (errors.size() > 0) {
			throw new BusinessException("Errores al realizar el retiro.", errors);
		} 
		
		Cuenta cuenta = cuentaRepository.get(nroCuenta);
		Transaccion transaccion = new Transaccion();
		List<Transaccion> transacciones = cuenta.getTransacciones();
		
		TipoTransaccionDTO tipoTransaccionDto = TipoTransaccionService.findById("Deposito");
		TipoTransaccion tipoTransaccion = new TipoTransaccion();
		tipoTransaccion.setNombreTipoTransaccion(tipoTransaccionDto.getNombreTipoTransaccion());
		tipoTransaccion.setDescripcionTipoTransaccion(tipoTransaccionDto.getDescripcionTipoTransaccion());
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setFechaTransaccion(new Date());
		transaccion.setMontoTransaccion(monto);
		transacciones.add(transaccion);
		
		cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() - monto);
		cuenta.setTransacciones(transacciones);
		
	}

	@Override
	public void transferir(String nroCuenta, String cuentaDestino, double monto) throws BusinessException{
		List<ValidationError> errors = validator.validarTransferencia(nroCuenta, cuentaDestino, monto);
		if (errors.size() > 0) {
			throw new BusinessException("Errores al realizar el transferencia.", errors);
		} 
		
		Cuenta cuenta = cuentaRepository.get(nroCuenta);
		Cuenta destino = cuentaRepository.get(cuentaDestino);
		
		Transaccion transaccionOrigen = new Transaccion();
		Transaccion transaccionDestino = new Transaccion();
		List<Transaccion> transaccionesOrigen = cuenta.getTransacciones();
		List<Transaccion> transaccionesDestino = cuenta.getTransacciones();
		
		//Creacion y asignacion de transaccion origen
		TipoTransaccionDTO tipoTranDtoOrigen = TipoTransaccionService.findById("Deposito");
		TipoTransaccion tipoTransaccionOrigen = new TipoTransaccion();
		tipoTransaccionOrigen.setNombreTipoTransaccion(tipoTranDtoOrigen.getNombreTipoTransaccion());
		tipoTransaccionOrigen.setDescripcionTipoTransaccion(tipoTranDtoOrigen.getDescripcionTipoTransaccion());
		transaccionOrigen.setFechaTransaccion(new Date());
		transaccionOrigen.setMontoTransaccion(monto);
		transaccionOrigen.setTipoTransaccion(tipoTransaccionOrigen);
		transaccionesOrigen.add(transaccionOrigen);
		
		//Creacion y asignacion de transaccion destino
		TipoTransaccionDTO tipoTranDtoDestino = TipoTransaccionService.findById("Deposito");
		TipoTransaccion tipoTransaccionDestino = new TipoTransaccion();
		tipoTransaccionDestino.setNombreTipoTransaccion(tipoTranDtoDestino.getNombreTipoTransaccion());
		tipoTransaccionDestino.setDescripcionTipoTransaccion(tipoTranDtoDestino.getDescripcionTipoTransaccion());
		transaccionDestino.setFechaTransaccion(new Date());
		transaccionDestino.setMontoTransaccion(monto);
		transaccionDestino.setTipoTransaccion(tipoTransaccionDestino);
		transaccionesDestino.add(transaccionDestino);
		
		//Actualizacion de las cuentas origen y destino de la transferencia
		cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() - monto);
		cuenta.setTransacciones(transaccionesOrigen);
		destino.setSaldoCuenta(destino.getSaldoCuenta() + monto);
		cuenta.setTransacciones(transaccionesDestino);
		
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
