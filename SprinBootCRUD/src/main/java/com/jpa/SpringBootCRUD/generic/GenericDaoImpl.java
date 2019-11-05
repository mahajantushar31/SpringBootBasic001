package com.jpa.SpringBootCRUD.generic;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/*public class GenericDaoImpl implements IGenericDao<E, K> {

	@Override
	public void add(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(E entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E find(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
*/

public class GenericDaoImpl<E,K> implements GenericDao<E,K> {

    
    protected Class<E> entityClass;
    
	EntityManagerFactory entityManagerFactory;

	EntityManager entityManager;
    

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	
	public GenericDaoImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	public void persist(E entity) {
		// getJpaTemplate().persist(entity);
		getEntityManager().persist(entity);
		getEntityManager().flush();
	}

	public void remove(E entity) {
		// getJpaTemplate().remove(entity);
		getEntityManager().remove(entity);
		getEntityManager().flush();

	}

	public E merge(E entity) {
		// return getJpaTemplate().merge(entity);
		E e = getEntityManager().merge(entity);
		getEntityManager().flush();
		return e;
	}

	public void refresh(E entity) {
		// getJpaTemplate().refresh(entity);
		getEntityManager().refresh(entity);
		getEntityManager().flush();
	}

	public E findById(K id) {
		// return getJpaTemplate().find(entityClass, id);
		return getEntityManager().find(entityClass, id);
	}

	public E flush(E entity) {
		// getJpaTemplate().flush();
		getEntityManager().flush();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {

		return getEntityManager().createQuery(
				"SELECT h FROM " + entityClass.getName()
						+ " h WHERE isActive=1").getResultList();

		// return (List<E>) res;
	}

	@SuppressWarnings("unchecked")
	public List<E> find(final int startFrom, final int maxResults) {

		Query q = getEntityManager().createQuery(
				"SELECT h FROM " + entityClass.getName()
						+ " h WHERE isActive=1 ORDER BY h.id DESC");
		q.setMaxResults(maxResults);
		q.setFirstResult(startFrom);
		return (List<E>) q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<E> findAllByTenant(String tenantId) {

		return getEntityManager()
				.createQuery(
						"SELECT h FROM "
								+ entityClass.getName()
								+ " h WHERE tenantId=:tenantId AND isActive=1")
				.setParameter("tenantId", tenantId).getResultList();
	}

	public List<E> findDisabled(String tenantId) {
		Query q = (Query) getEntityManager().createQuery(
				"SELECT h FROM " + entityClass.getName()
						+ " h WHERE tenantId=:tenantId AND isActive=false"
						+ " ORDER BY h.id DESC").setParameter("tenantId",
				tenantId);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<E> findAllByTenantPg(String tenantId, int startFrom,
			int maxResults) {
		Query q = (Query) getEntityManager().createQuery(
				"SELECT h FROM " + entityClass.getName()
						+ " h WHERE tenantId=:tenantId AND isActive=true "
						+ " ORDER BY h.id DESC").setParameter("tenantId",
				tenantId);
		q.setFirstResult(startFrom);
		q.setMaxResults(maxResults);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<E> findDisabledPg(String tenantId, int startFrom, int maxResults) {
		Query q = (Query) getEntityManager().createQuery(
				"SELECT h FROM " + entityClass.getName()
						+ " h WHERE tenantId=:tenantId AND isActive=false "
						+ " ORDER BY h.id DESC").setParameter("tenantId",
				tenantId);
		q.setFirstResult(startFrom);
		q.setMaxResults(maxResults);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Integer removeAll() {
		// return (Integer) getJpaTemplate().execute(new JpaCallback() {
		// return (Integer) getEntityManager().execute(new JpaCallback() {
		// public Object doInJpa(EntityManager em) throws PersistenceException {
		// Query q = em.createQuery("DELETE FROM " +
		// entityClass.getName() + " h");
		// return q.executeUpdate();
		// }
		//
		// });
		return 0;
	}

	public List<E> findByNamedQueryAndNamedParams(final String name,
			final Map<String, ? extends Object> params) {
		javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (final Map.Entry<String, ? extends Object> param : params
				.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}

		final List<E> result = (List<E>) query.getResultList();
		return result;
	}

	
	/*
	 *TO FIND COUNT USING COUNT(*) QUERY
	 */
	public Integer findCount(final String name,
			final Map<String, ? extends Object> params) {
		javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (final Map.Entry<String, ? extends Object> param : params
				.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}

		final Long result = (Long) query.getSingleResult();
		Integer res = 0;
		if(null == result)
			res = 0;
		else
			res = result.intValue();
		return res;
	}
	
	/**
	 * Find using a named query.
	 * 
	 * @param name
	 *            the name
	 * @param params
	 *            the query parameters
	 * @param startFrom
	 *            the start from
	 * @param maxResults
	 *            the max results
	 * @return the list of entities
	 */
	public List<E> findByNamedQueryAndNamedParams(final String name,
			final Map<String, ? extends Object> params, int startFrom,
			int maxResults) {
		javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (final Map.Entry<String, ? extends Object> param : params
				.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		query.setMaxResults(maxResults);
		query.setFirstResult(startFrom);

		final List<E> result = (List<E>) query.getResultList();
		return result;
	}

	public int recordCount(final String name,
			final Map<String, ? extends Object> params) {
		javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);
		for (final Map.Entry<String, ? extends Object> param : params
				.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		return ((Long) query.getSingleResult()).intValue();
	}

	/**
	 * Save the records in the DB and remove the entities from the memory. This
	 * method is useful for batch operation.
	 */
	public void flush() {
		getEntityManager().flush();
		getEntityManager().clear();
	}
	
	
	// implementation

/*	@Override
	public void persist(T entity) {    //save
		// TODO Auto-generated method stub
		entityManager.persist(entity);
		entityManager.flush();
	}

	@Override
	public void remove(T entity) {    //delete
		// TODO Auto-generated method stub
		entityManager.remove(entity);
		entityManager.flush();
	}

	@Override
	public T merge(T entity) {   // update
		T e = getEntityManager().merge(entity);
		getEntityManager().flush();
		return e;
	}
*/
	
}
