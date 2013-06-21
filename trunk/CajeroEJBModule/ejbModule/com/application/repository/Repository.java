package com.application.repository;

import java.util.List;


/**
 * Interfaz Repository para acceder a las entidades
 * @author Rulo
 * 
 * @param <T> Tipo de objeto de la entidad administrada.
 * @param <K> Tipo de datos utilizado como identidad del objeto.
 */
public interface Repository<T, K>{
	
	/**
	 * Busca en el Repository por la entidad T cuyo ID coincide con el parametro K
	 * @param id (id del objeto a buscar)
	 * @return 
	 */
	public T get(K id);

	/**
	 * Devuelve la lista con todas las entidades T en el Repository
	 * @return
	 */
	public List<T> listAll();
	
}
