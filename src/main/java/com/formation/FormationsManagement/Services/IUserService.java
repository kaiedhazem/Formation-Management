package com.formation.FormationsManagement.Services;

import java.util.List;

import com.formation.FormationsManagement.Entities.*;

public interface IUserService {

	User saveUser(User user);
	List<User> findAllUsers();
	void deleteUser(Long id);
	void addUser(User user);
	
}
