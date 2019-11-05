package com.jpa.SpringBootCRUD.dao;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.jpa.SprinBootCRUD.entity.User;
import com.jpa.SpringBootCRUD.generic.GenericDaoImpl;

public class UserDao extends  GenericDaoImpl<User,Long> implements IUserDao {

	private EntityManager entityManager;
	
	private EntityManagerFactory entityManagerFactory;
	

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

	@PostConstruct
	public void init() {
		super.setEntityManagerFactory(entityManagerFactory);
		super.setEntityManager(entityManager);
	}

	@Override
	public User save(User account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
