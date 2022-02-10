package com.gcu.business;

/**
 * business service interface for user specific functions
 * 
 * @author derek lundy
 *
 * @param <T>
 */
public interface ItemBusinessServiceInterface<T> {
	/**
	 * function to complete an item
	 * 
	 * @param itemId
	 * @return T
	 */
	public int complete(int itemId);
}
