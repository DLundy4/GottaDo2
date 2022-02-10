package com.gcu.business;

import java.util.List;

/**
 * business service interface
 * 
 * @author derek lundy
 *
 * @param <T>
 */
public interface BusinessServiceInterface<T> {
	/**
	 * function for finding all items in the database
	 * 
	 * @return List T
	 */
	public List<T> findAll();

	/**
	 * function for finding a specific object
	 * 
	 * @param id
	 * @return T
	 */
	public T findById(int id);

	/**
	 * inserts an object into the database
	 * 
	 * @param t
	 * @return int
	 */
	public int register(T t);

	/**
	 * updates an item in the database
	 * 
	 * @param t
	 * @return int
	 */
	public int edit(T t);

	/**
	 * removes an item from the database
	 * 
	 * @param t
	 * @return int
	 */
	public int remove(T t);
}