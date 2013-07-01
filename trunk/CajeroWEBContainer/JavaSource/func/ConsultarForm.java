package func;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.cuentas.CuentaService;
import com.cuentas.dto.ConsultaDTO;
import com.seguridad.dto.TarjetaDTO;

/**
 * Formulario de consulta de estado de cuenta que muestra el saldo y las transacciones asociadas.
 * @author Rulo
 *
 */
@ManagedBean
@SessionScoped
public class ConsultarForm {
	
	@EJB
	private CuentaService cuentaService;
	
	private String nroCuenta;

	public ConsultaDTO getConsulta(){
	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		TarjetaDTO tarjDTO = (TarjetaDTO) facesContext.getExternalContext().getSessionMap().get("nroTarjeta");
		String nroTarjetaTemp = tarjDTO.getNroTarjeta();
		cuentaService.findByNroTarjeta(nroTarjetaTemp).getNroCuenta();
		try{
			return cuentaService.consultar(cuentaService.findByNroTarjeta(nroTarjetaTemp).getNroCuenta());
		} catch (BusinessException be){
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
	
	public String getNroCuenta(){
		return nroCuenta;
	}
	
}
