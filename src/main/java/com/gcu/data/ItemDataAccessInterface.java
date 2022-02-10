package com.gcu.data;

/**
 * interface for item specific database operations
 * 
 * @author derek lundy
 *
 */
public interface ItemDataAccessInterface {
	/**
	 * function for completing an item
	 * 
	 * @param itemId
	 * @param userId
	 * @return int
	 */
	public int complete(int itemId);
}
