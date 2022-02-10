package com.gcu.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="items")
public class ItemList {
	
	private List<ItemModel> items = new ArrayList<ItemModel>();
	
	@XmlElement(name="item")
	public List<ItemModel> getItems()
	{
		return this.items;
	}
	
	public void setItems(List<ItemModel> items)
	{
		this.items = items;
	}

}
