package func;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.application.exceptions.BusinessException;
import com.cuentas.CuentaService;

/**
 * Formulario para llevar a cabo una transaccion de una cuenta a otra.
 * @author Rulo
 *
 */
@ManagedBean
@SessionScoped
public class TransferirForm {
	
	@EJB
	private CuentaService cuentaService;
	
	private String cuentaOrigen;
	private String cuentaDestino;
	private double monto;
	
	public String transferir(){
		try{
			cuentaService.transferir(cuentaOrigen, cuentaDestino, monto);
			return "";
		} catch (BusinessException be){
			processBussinessException(be);
		}
		return null;
	}

	private void processBussinessException(BusinessException be) {
		
		
	}

}
