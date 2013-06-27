package func;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.cuentas.CuentaService;


/**
 * Formulario para llevar a cabo una extraccion en un cuenta.
 * @author Rulo
 *
 */
@ManagedBean
@SessionScoped
public class RetirarForm {
	
	@EJB
	private CuentaService cuentaService;
	
	private String nroCuenta;
	private double monto;
	
	
	
	public String retirar(){
		try {
		cuentaService.retirar(nroCuenta, monto);
		return "";
		} catch (BusinessException be) {
			processBusinessException(be);
		}
		return null;
	}



	private void processBusinessException(BusinessException be) {
		
		if (be.getErrores().size() > 0) {
			for (ValidationError ve : be.getErrores()) {
				FacesContext.getCurrentInstance().addMessage("form:" + ve.getProperty(), new FacesMessage(FacesMessage.SEVERITY_ERROR, ve.getError(), ve.getError()));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, be.getMessage(), be.getMessage()));
		}
	}
	

}
