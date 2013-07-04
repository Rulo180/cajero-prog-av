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
	private double saldo;
	private double monto;
	
	
	
	public String retirar(){
		try {
		cuentaService.retirar(nroCuenta, monto);
		return "/menu.xhtml?faces-redirect=true";
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
	
	@PostConstruct
	private void initialize() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		TarjetaDTO tarjDTO = (TarjetaDTO) facesContext.getExternalContext().getSessionMap().get("nroTarjeta");
		String nroTarjeta = tarjDTO.getNroTarjeta();
		this.nroCuenta = cuentaService.findByNroTarjeta(nroTarjeta).getNroCuenta();
		this.saldo = cuentaService.findByNroTarjeta(nroTarjeta).getSaldoCuenta();
		
	}
	
	public String getNroCuenta(){
		return nroCuenta;
	}
	
	public double getMonto(){
		return monto;
	}



	public double getSaldo() {
		return saldo;
	}

}
