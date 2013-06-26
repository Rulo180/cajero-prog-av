package com.cuentas;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.cuentas.dto.TipoTransaccionDTO;
import com.cuentas.dto.TipoTransaccionDTOFactory;
import com.cuentas.repository.TipoTransaccionRepository;


@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TipoTransaccionService {

	@EJB
	private TipoTransaccionRepository tipoTransaccionRepository;
	
	public void TipoTransaccionRepository(){
		
	}
	
	public TipoTransaccionDTO findById(String nombreTipo) {
		
		return TipoTransaccionDTOFactory.getTipoTransaccionDTO(tipoTransaccionRepository.get(nombreTipo));

	}
	
	public static Collection<TipoTransaccionDTO> listAll(){
		
		return TipoTransaccionRepository.listAll();
		
	}

}
