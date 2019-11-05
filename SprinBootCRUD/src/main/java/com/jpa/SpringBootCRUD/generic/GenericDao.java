package com.jpa.SpringBootCRUD.generic;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

public interface GenericDao<E,K> {
/*	
	public void persist(T entity);  //T save();

	public void remove(T entity);   // void delete(T t);

	public T merge(T entity); 		// T merge();  T update(T t); 

	public void refresh(T entity); // refresh (User  userObj)
	*/

	public void persist(E entity);

	public void remove(E entity);

	public E merge(E entity);

	public void refresh(E entity);

	public E findById(K id);

	public E flush(E entity);

	public void flush();

	public List<E> findAll();

	public List<E> find(int startFrom, int maxResults);

	public List<E> findAllByTenant(String tenantId);

	public List<E> findAllByTenantPg(String tenantId, int startFrom,
			int maxResults);

	public List<E> findDisabled(String tenantId);

	public List<E> findDisabledPg(String tenantId, int startFrom, int maxResults);

	public Integer removeAll();

	public void setEntityManager(EntityManager entityManager);

	public List<E> findByNamedQueryAndNamedParams(final String queryName,
			final Map<String, ? extends Object> params);
	
	//TO BE USED FOR RUNNING COUNT QUERY
	public Integer findCount(final String queryName,
			final Map<String, ? extends Object> params);

	public List<E> findByNamedQueryAndNamedParams(final String queryName,
			final Map<String, ? extends Object> params, int startFrom,
			int maxResults);

	//this query not to be used as it does not have any null handling
	public int recordCount(final String name,
			final Map<String, ? extends Object> params);
    
}