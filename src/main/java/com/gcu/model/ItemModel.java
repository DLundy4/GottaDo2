package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * description of all properties and functions of a user
 * @author derek lundy
 *
 */
public class ItemModel implements Comparable<ItemModel> {
	
	private int id;
	@NotNull(message = "Item Name is a required field")
	@Size(min = 5, max = 32, message = "Item Name must be between 5 and 32 characters")
	private String name;
	@NotNull(message = "Item Description is a required field")
	@Size(min = 20, max = 500, message = "Item Description must be between 5 and 32 characters")
	private String description;
	private int status;
	
	private int userId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int rank) {
		this.status = rank;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public ItemModel(int id, String name, String description, int status) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	public ItemModel(int id, String name, String description, int status, int userId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.userId = userId;
	}
	
	public ItemModel() {
		super();
		this.id = -1;
		this.name = "";
		this.description = "";
		this.status = -1;
		this.userId = -1;
	}
	
	@Override
	public int compareTo(ItemModel o) {
		return o.status - this.status;
	}
}
