package com.jpa.SprinBootCRUD.service;

import java.util.List;

import com.jpa.SprinBootCRUD.entity.User;

public interface UserService {
	void createUser(User user);
	List<User> getAllUsers();
	User getUser(User user);
	
	
}
