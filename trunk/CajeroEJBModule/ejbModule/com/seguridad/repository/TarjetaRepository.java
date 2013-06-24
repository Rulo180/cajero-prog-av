package com.seguridad.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repository;
import com.cuentas.entities.Tarjeta;

/**
 * Repositorio de Almacenamiento de Usuarios del Sistema.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
public class TarjetaRepository implements Repository<Tarjeta, String> {
	@PersistenceContext(unitName = "cajeroDS")
	private EntityManager entityManager;

	@Override
	public Tarjeta get(String nroTarjeta) {
		return entityManager.find(Tarjeta.class, nroTarjeta);
	}

	@Override
	public List<Tarjeta> listAll() {
		String q = "SELECT p from " + Tarjeta.class.getName() + " p ";
		TypedQuery<Tarjeta> query = entityManager.createQuery(q, Tarjeta.class);

		List<Tarjeta> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Tarjeta>();
		}
		return result;
	}
	

	
}
