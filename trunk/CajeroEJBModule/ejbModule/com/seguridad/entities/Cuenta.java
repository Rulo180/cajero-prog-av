package com.seguridad.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.crypto.CryptoUtil;

import com.seguridad.dto.RolSeguridad;

/**
 * Cuentas del sistema, esta entidad es utilizada por los mecanismos de
 * seguridad para realizar el login en el sistema.
 * 
 * Cada cuenta posee una lista de roles del sistema que tiene habilitados.
 */
@Entity
public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty()
	@Size(max = 12)
	private String nroCuenta;

	@NotEmpty()
	@Size(max = 128)
	private String titularCuenta;
	
	@NotEmpty()
	private Double saldoCuenta;

	@NotNull()
	@Temporal(TemporalType.DATE)
	private Date fechaCreacionCuenta;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cuentaId")
	private List<Rol> roles;

	public Cuenta() {
		super();
	}
	
}