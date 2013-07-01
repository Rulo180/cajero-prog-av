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
import com.cuentas.dto.ConsultaDTO;
import com.cuentas.dto.ConsultaDTOFactory;
import com.cuentas.dto.CuentaDTO;
import com.cuentas.dto.CuentaDTOFactory;
import com.cuentas.entities.Cuenta;
import com.cuentas.entities.Tarjeta;
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
	public ConsultaDTO consultar(String nroCuenta) throws BusinessException {
		List<ValidationError> errors = validator.validarCuenta(nroCuenta);
		if (errors.size() > 0) {
			throw new BusinessException("Errores al cargar la cuenta.", errors);
		} 
		
		double saldo = cuentaRepository.get(nroCuenta).getSaldoCuenta();
		Collection<Transaccion> transacciones = cuentaRepository.get(nroCuenta).getTransacciones();
		
		return ConsultaDTOFactory.getConsultaDTO(nroCuenta, saldo, transacciones);
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
		
		transaccion.setTipoTransaccion(com.cuentas.dto.TipoTransaccion.EXTRACCION);
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
		
		transaccion.setTipoTransaccion(com.cuentas.dto.TipoTransaccion.EXTRACCION);
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
		transaccionOrigen.setFechaTransaccion(new Date());
		transaccionOrigen.setMontoTransaccion(monto);
		transaccionOrigen.setTipoTransaccion(com.cuentas.dto.TipoTransaccion.TRANSFERENCIA_EFECTUADA);
		transaccionesOrigen.add(transaccionOrigen);
		
		//Creacion y asignacion de transaccion destino
		transaccionDestino.setFechaTransaccion(new Date());
		transaccionDestino.setMontoTransaccion(monto);
		transaccionDestino.setTipoTransaccion(com.cuentas.dto.TipoTransaccion.TRANSFERENCIA_RECIBIDA);
		transaccionesDestino.add(transaccionDestino);
		
		//Actualizacion de las cuentas origen y destino de la transferencia
		cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() - monto);
		cuenta.setTransacciones(transaccionesOrigen);
		destino.setSaldoCuenta(destino.getSaldoCuenta() + monto);
		cuenta.setTransacciones(transaccionesDestino);
		
	}

	@Override
	public CuentaDTO findByNroCuenta(String nroCuenta) {
		return CuentaDTOFactory.getCuentaDTO(cuentaRepository.get(nroCuenta));
	}

	@Override
	public CuentaDTO findByNroTarjeta(String nroTarjeta) {
		List<Cuenta> listaC = cuentaRepository.listAll();
		
		for (Cuenta cuenta : listaC){
			for (Tarjeta tarjeta : cuenta.getTarjetas()){
				if (tarjeta.getNroTarjeta() == nroTarjeta)
					break;
			}
			return CuentaDTOFactory.getCuentaDTO(cuenta);
		}
		return null;
	}
    
	

}
