package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.model.ItemList;
import com.gcu.model.ItemModel;

/**
 * rest service for GottaDo
 * 
 * @author derek lundy
 *
 */
@RestController
@RequestMapping("/service/item")
public class ItemsRestService {

	@Autowired
	BusinessServiceInterface<ItemModel> service;

	/**
	 * Retrieve all Items and display in JSON format
	 * 
	 * @return List ItemModel 
	 */
	@GetMapping(path = "/getjson", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ItemModel> getAllItemsAsJson() {
		return service.findAll();
	}

	/**
	 * Retrieve all Items and display in XML format
	 * 
	 * @return ItemList
	 */
	@GetMapping(path = "/getxml", produces = { MediaType.APPLICATION_XML_VALUE })
	public ItemList getAllItemsAsXML() {
		ItemList list = new ItemList();
		list.setItems(service.findAll());
		return list;
	}

}
