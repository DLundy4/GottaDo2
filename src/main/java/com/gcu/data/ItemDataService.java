package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import com.gcu.model.ItemModel;

/**
 * Data service for performing database item operations.
 * 
 * @author derek lundy
 *
 */
@Service
public class ItemDataService implements DataAccessInterface<ItemModel>, ItemDataAccessInterface {

	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	// Non-Default constructor to create the data service.
	public ItemDataService(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/**
	 * Insert user created item into DB
	 */
	@Override
	public int create(ItemModel item) {
		// SQL query to insert new item into the DB
		String sql = "INSERT INTO items(itemName, itemDescription, itemStatus, item_userId) VALUES (?,?,?,?)";
		try {
			// Set rows equal to the item properties
			int rows = jdbcTemplateObject.update(sql, item.getName(), item.getDescription(), item.getStatus(), item.getUserId());
			// check if the item was inserted
			return rows == 1 ? 1 : 0;
		} catch (Exception e) {
			e.printStackTrace();
			// return -1 if cannot connect to database
			return -1;
		}

	}

	/**
	 * find the item from the provided id
	 */
	@Override
	public ItemModel findById(int id) {
		// SQL query to select the category that has an id of the passed in 'id'
		// variable
		String sql = "SELECT * FROM items WHERE itemID = '" + id + "'";
		// Create new CategoryModel to hold retrieved values with default values
		ItemModel item = new ItemModel();
		try {
			// Execute query and set it to a SqlRowSet object
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			// Loop through results
			while (srs.next()) {
				// Set CategoryModel properties to the returned category properties from DB.
				item.setId(srs.getInt("itemID"));
				item.setStatus(srs.getInt("itemStatus"));
				item.setName(srs.getString("itemName"));
				item.setDescription(srs.getString("itemDescription"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return retrieved category
		return item;
	}

	/**
	 * function for updating an item in the database
	 */
	@Override
	public int update(ItemModel t) {
		// SQL query to update specific item in the DB
		String sql = "UPDATE items SET itemName = ?, itemDescription = ?, itemStatus = ? WHERE itemID = ?";
		try {

			// return num rows affected
			return jdbcTemplateObject.update(sql, t.getName(), t.getDescription(), t.getStatus(), t.getId());
		} catch (Exception e) {
			e.printStackTrace();
			// return -1 if cannot connect to database
			return -1;
		}
	}

	/**
	 * deletes and item from the database
	 */
	@Override
	public int delete(ItemModel t) {
		// SQL query to delete item from DB
		String sql = "DELETE FROM items WHERE itemID = ?";
		try {
			// return num rows affected
			return jdbcTemplateObject.update(sql, t.getId());
		} catch (Exception e) {
			e.printStackTrace();
			// return -1 if cannot connect to database
			return -1;
		}
	}

	/**
	 * retrieve all of the items
	 */
	@Override
	public List<ItemModel> findAll() {
		// SQL query to select all items in DB
		String sql = "SELECT * FROM items";
		// Variable to hold retrieved items
		List<ItemModel> items = new ArrayList<ItemModel>();
		try {
			// Execute query and set it to a SqlRowSet object
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			// Loop through results
			while (srs.next()) {
				// Add every category to 'items' List with ItemModel created using
				// retrieved row data.
				items.add(new ItemModel(srs.getInt("itemID"), srs.getString("itemName"),
						srs.getString("itemDescription"), srs.getInt("itemStatus")));
			}
		} catch (Exception e) {
			items.add(new ItemModel(-1, "Database Error", "There was an error connecting to the database", -1));
			e.printStackTrace();
		}
		// Return list of retrieved items
		return items;
	}

	/**
	 * function for handling completing an item
	 */
	@Override
	public int complete(int itemId) {
		// SQL query to update specific item in the DB
		String sql = "UPDATE items SET itemStatus = ? WHERE itemID = ?";
		try {
			// return num rows affected
			return jdbcTemplateObject.update(sql, 1, itemId);
		} catch (Exception e) {
			e.printStackTrace();
			// return -1 if cannot connect to database
			return -1;
		}
	}

}
