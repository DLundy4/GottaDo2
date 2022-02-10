package com.gcu.data;

/**
 * interface for user specific database operations
 * 
 * @author derek lundy and collin willis
 *
 * @param generic
 */
public interface UserDataAccessInterface<T> {
	/**
	 * Return user that contains provided username and password
	 * 
	 * @param username
	 * @return generic
	 */
	public T findByUsername(String username);
}
