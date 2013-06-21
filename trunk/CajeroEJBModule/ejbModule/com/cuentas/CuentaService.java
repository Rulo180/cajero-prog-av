package com.cuentas;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.cuentas.dto.CuentaDTO;
import com.cuentas.entities.Cuenta;
import com.cuentas.repository.CuentaRepository;

/**
 * Session Bean implementation class CuentaService
 */
@Stateless
@LocalBean
public class CuentaService implements CuentaServiceRemote {
	
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
	public double getSaldo(String nroCuenta) {
		return cuentaRepository.get(nroCuenta).getSaldoCuenta();
	}

	@Override
	public void depositar(String nroCuenta, double monto) {
		Cuenta cuenta = cuentaRepository.get(nroCuenta);
		cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() + monto);
	}

	@Override
	public void retirar(String nroCuenta, double monto) {
		Cuenta cuenta = cuentaRepository.get(nroCuenta);
		cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() - monto);
	}

	@Override
	public void transferir(String nroCuenta, String cuentaDestino, double monto) {
		
	}

	@Override
	public Collection<CuentaDTO> listCuentas() {
		return null;
	}

	@Override
	public Cuenta findByNroCuenta(String nroCuenta) {
		return null;
	}
    
	

}
