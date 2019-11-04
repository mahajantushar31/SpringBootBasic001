package com.jpa.SpringBootCRUD.dao;

import java.io.Serializable;

import com.jpa.SprinBootCRUD.entity.User;
import com.jpa.SpringBootCRUD.generic.GenericDao;

public interface IUserDao extends GenericDao<User >{
	 User save(User account);
	 User findByName(String name);
}
