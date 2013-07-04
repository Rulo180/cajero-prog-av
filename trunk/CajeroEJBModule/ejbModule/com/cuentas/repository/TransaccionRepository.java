package com.cuentas.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repository;
import com.cuentas.entities.Transaccion;

@Stateless
@LocalBean
public class TransaccionRepository implements Repository<Transaccion, Integer>{
	
	@PersistenceContext(unitName = "cajeroDS")
	private EntityManager manager;

	@Override
	public Transaccion get(Integer id) {
		return manager.find(Transaccion.class, id);
	}

	@Override
	public List<Transaccion> listAll() {
		String q = "SELECT p from " + Transaccion.class.getName() + " p ";
		TypedQuery<Transaccion> query = manager.createQuery(q, Transaccion.class);

		List<Transaccion> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Transaccion>();
		}
		return result;
	}

	public void save(Transaccion t){
		manager.persist(t);
	}

}
