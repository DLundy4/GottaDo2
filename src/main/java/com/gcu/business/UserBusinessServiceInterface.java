package com.gcu.business;

/**
 * business service interface for user specific funtions
 * 
 * @author derek lundy
 *
 * @param <T>
 */
public interface UserBusinessServiceInterface<T> {
	/**
	 * funciton to retrieve the user by username
	 * 
	 * @param username
	 * @return T
	 */
	public T getUserByUsername(String username);
}
