package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import com.gcu.model.UserModel;

/**
 * Data service for performing database user operations.
 * 
 * @author derek lundy
 *
 */
@Service
public class UserDataService implements DataAccessInterface<UserModel>, UserDataAccessInterface<UserModel> {

	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	// Non-Default constructor to create the data service.
	public UserDataService(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);

	}

	/**
	 * Retrieve all users from DB and return in a List
	 */
	@Override
	public List<UserModel> findAll() {
		// SQL query to select all users in DB
		String sql = "SELECT * FROM users";
		// Variable to hold retrieved users
		List<UserModel> users = new ArrayList<UserModel>();
		try {
			// Execute query and set it to a SqlRowSet object
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			// Loop through results
			while (srs.next()) {
				// Add every user to 'users' List with UserModels created using retrieved row
				// data.
				users.add(new UserModel(srs.getString("username"), srs.getString("password"), srs.getString("email"), srs.getInt("usersID")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return retrieved users
		return users;
	}

	/**
	 * insert user into the database
	 */
	@Override
	public int create(UserModel user) {
		// SQL query to insert new user into the DB
		String sql = "INSERT INTO users(username, password, email) VALUES (?,?,?)";
		try {
			// Set rows equal to the user properties
			int rows = jdbcTemplateObject.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
			// check if the user was inserted
			return rows == 1 ? 1 : 0;
		} catch (Exception e) {
			e.printStackTrace();
			// return -1 if there was a connection error
			return -1;
		}

	}

	/**
	 * find user in database by id
	 */
	@Override
	public UserModel findById(int id) {
		// SQL query to select the category that has an id of the passed in 'id'
		// variable
		String sql = "SELECT * FROM users WHERE userID = '" + id + "'";
		// Create new CategoryModel to hold retrieved values with default values
		UserModel user = new UserModel();
		try {
			// Execute query and set it to a SqlRowSet object
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			// Loop through results
			while (srs.next()) {
				// Set CategoryModel properties to the returned category properties from DB.
				user.setUsername(srs.getString("username"));
				user.setPassword(srs.getString("password"));
				user.setEmail(srs.getString("email"));
				user.setId(srs.getInt("userID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return retrieved category
		return user;
	}

	/**
	 * find user in database by username
	 */
	@Override
	public UserModel findByUsername(String username) {
		// SQL query to select the category that has a username of the passed in
		// 'username'
		// variable
		String sql = "SELECT * FROM users WHERE username = '" + username + "'";
		// Create new CategoryModel to hold retrieved values with default values
		UserModel user = new UserModel();
		try {
			// Execute query and set it to a SqlRowSet object
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			// Loop through results
			while (srs.next()) {
				// Set CategoryModel properties to the returned category properties from DB.
				user.setUsername(srs.getString("username"));
				user.setPassword(srs.getString("password"));
				user.setEmail(srs.getString("email"));
				user.setId(srs.getInt("userID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return retrieved category
		return user;
	}

	// ---------------BELOW NOT IN USE -------------------

	@Override
	public int update(UserModel t) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int delete(UserModel t) {
		// TODO Auto-generated method stub
		return -1;
	}

}
