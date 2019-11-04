package com.jpa.SpringBootCRUD.generic;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

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

public class GenericDaoImpl<T> 
    implements GenericDao<T> {

    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;
    
    @PersistenceContext
    protected EntityManagerFactory entityManagerFactory;

    
    public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
             .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public T create(T t) {
        this.entityManager.persist(t);
        return t;
    }

    

    @Override
    public T update(T t) {
        return this.entityManager.merge(t);
    }

    @Override
    public void delete(T t) {
        t = this.entityManager.merge(t);
        this.entityManager.remove(t);
    }
}
