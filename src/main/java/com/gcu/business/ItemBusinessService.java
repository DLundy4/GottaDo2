package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.ItemDataService;
import com.gcu.model.ItemModel;

/**
 * business service for item specific operations
 * 
 * @author derek lundy
 *
 */
public class ItemBusinessService implements BusinessServiceInterface<ItemModel>, ItemBusinessServiceInterface<ItemModel> {

	@Autowired
	ItemDataService itemDataService;

	/**
	 * Insert user created item into DB
	 */
	@Override
	public int register(ItemModel item) {
		// Return itemDataService create function results
		return itemDataService.create(item);
	}

	/**
	 * function for editing an item in the datbase
	 */
	@Override
	public int edit(ItemModel t) {
		return itemDataService.update(t);
	}

	/**
	 * function for finding an item by its id
	 */
	@Override
	public ItemModel findById(int id) {
		// TODO Auto-generated method stub
		return itemDataService.findById(id);
	}

	/**
	 * function for removing an item
	 */
	@Override
	public int remove(ItemModel t) {
		return itemDataService.delete(t);
	}

	/**
	 * Function for getting all the items
	 */
	@Override
	public List<ItemModel> findAll() {
		return itemDataService.findAll();
	}

	/**
	 * Function for completing an item
	 */
	@Override
	public int complete(int itemId) {
		return itemDataService.complete(itemId);
	}
}
