package security;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.application.exceptions.BusinessException;
import com.seguridad.SeguridadService;
import com.seguridad.dto.TarjetaDTO;

@ManagedBean
@RequestScoped
public class Authentication {
	@EJB
	private SeguridadService seguridadService;

	private String nroTarjeta;
	private String pinTarjeta;

	/**
	 * Procesa el login de la pagina.
	 * 
	 * @throws IOException
	 */
	public String login() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

		try {
			TarjetaDTO tarj = seguridadService.login(nroTarjeta);
			request.login(nroTarjeta, pinTarjeta);
			externalContext.getSessionMap().put("nroTarjeta", tarj);
		    return "/menu.xhtml?faces-redirect=true";
		} catch (BusinessException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Tarjeta o Pin invalido."));
		}
		return null;
	}

	/**
	 * Logout, limpia la sesion.	
	 * 
	 * @throws IOException
	 */
	public String logout() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.invalidateSession();
	    return "/index.xhtml?faces-redirect=true";
	}
	
	
	public String getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public String getPinTarjeta() {
		return pinTarjeta;
	}
	public void setPinTarjeta(String pinTarjeta) {
		this.pinTarjeta = pinTarjeta;
	}
}