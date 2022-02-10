package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * description of all properties and functions of a user
 * @author derek lundy
 *
 */
public class UserModel {

	@NotNull(message = "Username is a required field")
	@Size(min = 5, max = 32, message = "Username must be between 5 and 32 characters")
	private String username;
	@NotNull(message = "Password is a required field")
	@Size(min = 5, max = 32, message = "Password must be betweem 5 and 32 characters")
	private String password;
	@NotNull(message = "Email is a required field")
	@Size(min = 5, max = 32, message = "Email must be betweem 5 and 32 characters")
	private String email;

	private int id;

	/**
	 * get the username of the user
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * set the username of the user
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * get the password of the user
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set the password of the user
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * get the email of the user
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set the email of the user
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * get the id of the user
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * set the id of the user
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * non-default constructor of the user model
	 * @param username
	 * @param password
	 * @param email
	 * @param role
	 * @param id
	 */
	public UserModel(String username, String password, String email, int id) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.id = id;
	}

	/**
	 * default constructor for the user model
	 */
	public UserModel() {
		this.username = "";
		this.password = "";
		this.email = "";
		this.id = -1;
	}

}
