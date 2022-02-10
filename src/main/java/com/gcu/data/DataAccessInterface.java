package com.gcu.data;

import java.util.List;

/**
 * Interface for data access classes
 * 
 * @author derek lundy
 *
 * @param <T>
 */
public interface DataAccessInterface<T> {
	/**
	 * function for returning a list of objects
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * function for finding an object by id
	 * 
	 * @param id
	 * @return
	 */
	public T findById(int id);

	/**
	 * function for inserting an item into the database
	 * 
	 * @param t
	 * @return
	 */
	public int create(T t);

	/**
	 * function for updating an item in the database
	 * 
	 * @param t
	 * @return
	 */
	public int update(T t);

	/**
	 * function for deleting an in the database
	 * 
	 * @param t
	 * @return
	 */
	public int delete(T t);
}
