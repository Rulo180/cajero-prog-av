package com.seguridad.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repository;
import com.seguridad.entities.Cuenta;

/**
 * Repositorio de Almacenamiento de Usuarios del Sistema.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
public class CuentaRepository implements Repository<String, Cuenta> {
	@PersistenceContext(unitName = "cajeroDS")
	private EntityManager entityManager;
	
	@Override
	public void persist(Cuenta entity) {

		entityManager.persist(entity);
	}
	
	@Override
	public void remove(Cuenta entity) {
		entityManager.remove(entity);
	}
	
}
