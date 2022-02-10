package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.UserDataService;
import com.gcu.model.UserModel;

/**
 * Business service for user specific functions
 * 
 * @author derek lundy
 *
 */
public class UserBusinessService
		implements BusinessServiceInterface<UserModel>, UserBusinessServiceInterface<UserModel> {

	@Autowired
	UserDataService userDataService;

	/**
	 * Insert user creating account
	 */
	@Override
	public int register(UserModel t) {
		return userDataService.create(t);
	}

	/**
	 * function for finding the user by id
	 */
	@Override
	public UserModel findById(int id) {
		return userDataService.findById(id);
	}

	/**
	 * function for getting the user by username
	 */
	@Override
	public UserModel getUserByUsername(String username) {
		return userDataService.findByUsername(username);
	}
	
	/**
	 * function for getting all users
	 */
	@Override
	public List<UserModel> findAll() {
		return userDataService.findAll();
	}

	// --------------- BELOW NOT IN USE -------------------

	@Override
	public int edit(UserModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(UserModel t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
