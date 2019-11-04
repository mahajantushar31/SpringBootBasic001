package com.jpa.SprinBootCRUD.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mongo.MongoClientFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public  class JPARepoImpl<T> implements JpaRepository<T, Long> {

	private JpaRepository jpaDao;
	
	private EntityManager entityManager;
	
	private EntityManagerFactory entityManagerFactory;
	
	private Class<T> entityClass;
	
	private static final Logger logger=LoggerFactory.getLogger(JPARepoImpl.class);
	
	//-------------------- constructor
	public JPARepoImpl() {
		ParameterizedType genericSuperClass=(ParameterizedType)getClass().getGenericSuperclass();
		this.entityClass=(Class<T>)genericSuperClass.getActualTypeArguments()[1];
	}

	//----------------------------------------------
	//  set jpa EntityManager for dao
	/*public void setEntityManagerOnDao(EntityManager entityManager) {
	
	}*/
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	//--------------------------------------------------
	public JpaRepository getJpaDao() {
		return jpaDao;
	}

	public void setJpaDao(JpaRepository jpaDao) {
		this.jpaDao = jpaDao;
	}
	
	//-----------------------------------------------------
	
	
	// ----------------find ----------------------
	
	@Override
	public Optional<T> findById(Long id) {
	
		return (Optional<T>)jpaDao.findById(id);
		
	}
	// for mongo 
//	@Override
	/*public T find(ObjectId id) throws Exception {
		try {
			return (T) jpaDao.findById(id);
		} catch (Exception e) {
			logger.error("Error : " + e.getStackTrace(e));
			throw new Exception(entityClass.getName() + " - not found ", e);
		}
	}*/
	
	
	
	@Override
	public <S extends T> Optional<S> findOne(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAllById(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public T getOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page<T> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@Override
	public <S extends T> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	//-------------------------
	//----------------------- SAVE -------------------------

	@Override
	public <S extends T> S save(S entity) {
		try {
			//createAud (entity);
			return ((EntityManager) jpaDao).persist(entity);
		} catch (Exception e) {
			logger.error("Error : " + e.getStackTrace());
			throw new Exception(entityClass.getName() + " - ", e);
		}
	}
	
	
	
	 public void createAud(Object entity) {
    	 
	    	String e=entity.getClass().getName().toUpperCase() ;
	    	if(e.contains("CEPSCANRESULT")||e.contains("SOLRSCANRESULT")||e.contains("NGPRECASEMST")){
	    		return;
	    	}
	    	
	        /*BaseMongo mongoEntity = (BaseMongo) entity;*/
	    	
	        Class clazz = entity.getClass();
	        String collName = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1).toUpperCase();
	        //String audCollName = collName + OmniConstants.MAKERCHECKER_AUD;

	        //DBCollection dbColl = MongoClientFactory.getMongoClientFactory().getDBCollection(collName);
	        

	        DBObject existingRecord = dbColl.findOne(mongoEntity.getIdMongo());
	        if (existingRecord == null) {
	            return;
	        }

	        DBCollection audColl = MongoClientFactory.getMongoClientFactory().getDBCollection(audCollName);
	        BasicDBObject removeQuery = new BasicDBObject();
	        removeQuery.put(OmniConstants.MONNGO_ID_KEY, mongoEntity.getIdMongo());
	        audColl.remove(removeQuery);
	        audColl.insert(existingRecord);

	    }
	
	 public void persist(T entity) {
			EntityTransaction tx = getEntityManager().getTransaction();
			if (!tx.isActive()) {
				tx.begin();
			}
			getEntityManager().persist(entity);
			// getEntityManager().flush();
			tx.commit();
		}
	
	//-------------------- SAVE ------------------------------ 
	
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(T arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends T> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public <S extends T> long count(Example<S> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends T> boolean exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<T> arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends T> List<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends T> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
