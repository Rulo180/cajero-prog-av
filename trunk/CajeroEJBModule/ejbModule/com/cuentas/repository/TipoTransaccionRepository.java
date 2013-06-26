package com.cuentas.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repository;
import com.cuentas.entities.TipoTransaccion;

@Stateless
@LocalBean
public class TipoTransaccionRepository implements Repository<TipoTransaccion, String>{
	
	@PersistenceContext(unitName = "cajeroDS")
	private EntityManager manager;

	@Override
	public TipoTransaccion get(String id) {
		return manager.find(TipoTransaccion.class, id);
	}

	@Override
	public List<TipoTransaccion> listAll() {
		String q = "SELECT p from " + TipoTransaccion.class.getName() + " p ";
		TypedQuery<TipoTransaccion> query = manager.createQuery(q, TipoTransaccion.class);

		List<TipoTransaccion> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<TipoTransaccion>();
		}
		return result;
	}

}
