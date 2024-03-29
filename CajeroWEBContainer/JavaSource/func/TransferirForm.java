package func;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.cuentas.CuentaService;
import com.seguridad.dto.TarjetaDTO;

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
	private double saldoOrigen;
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
		
		if (be.getErrores().size() > 0) {
			for (ValidationError ve : be.getErrores()) {
				FacesContext.getCurrentInstance().addMessage("form:" + ve.getProperty(), new FacesMessage(FacesMessage.SEVERITY_ERROR, ve.getError(), ve.getError()));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, be.getMessage(), be.getMessage()));
		}
		
	}
	/**
	 * Inicializa los atributos que necesita el ManageBean con los parametros del contexto
	 */
	@PostConstruct
	private void initialize() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		TarjetaDTO tarjDTO = (TarjetaDTO) facesContext.getExternalContext().getSessionMap().get("nroTarjeta");
		String nroTarjeta = tarjDTO.getNroTarjeta();
		this.cuentaOrigen = cuentaService.findByNroTarjeta(nroTarjeta).getNroCuenta();
		this.saldoOrigen = cuentaService.findByNroTarjeta(nroTarjeta).getSaldoCuenta();
		
	}

	public String getCuentaOrigen(){
		return cuentaOrigen;
	}
	
	public String getCuentaDestino(){
		return cuentaDestino;
	}
	
	public double getMonto(){
		return monto;
	}
	
	public double getSaldoOrigen(){
		return saldoOrigen;
	}
}
