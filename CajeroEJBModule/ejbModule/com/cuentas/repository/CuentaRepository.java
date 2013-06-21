package com.cuentas.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.application.repository.Repository;
import com.cuentas.entities.Cuenta;

/**
 * Se encarga del manejo de las entidades con la BD
 * @author Rulo
 *
 */

@Stateless
@LocalBean
public class CuentaRepository implements Repository<Cuenta, String> {
	
	@PersistenceContext(unitName = "cajeroDS")
	private EntityManager manager;

	@Override
	public Cuenta get(String id) {
		return manager.find(Cuenta.class, id);
	}

	@Override
	public List<Cuenta> listAll() {
		String q = "SELECT p from " + Cuenta.class.getName() + " p ";
		TypedQuery<Cuenta> query = manager.createQuery(q, Cuenta.class);

		List<Cuenta> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Cuenta>();
		}
		return result;
	}

}
