package com.gcu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gcu.business.BusinessServiceInterface;
import com.gcu.business.ItemBusinessServiceInterface;
import com.gcu.business.UserBusinessServiceInterface;
import com.gcu.model.ItemModel;
import com.gcu.model.UserModel;

/**
 * Controller for all item functions
 * @author derek lundy
 *
 */
@Controller
@RequestMapping("/items")
public class ItemController {
	@Autowired
	BusinessServiceInterface<ItemModel> itemService;
	@Autowired
	BusinessServiceInterface<UserModel> userService;
	@Autowired
	UserBusinessServiceInterface<UserModel> customUserService;
	@Autowired
	ItemBusinessServiceInterface<ItemModel> customItemService;

	
	/**
	 * Displays the category page
	 * @param model
	 * @param user
	 * @return category
	 */
	@PostMapping("/display")
	public String display(Model model, UserModel user) {
		UserModel loggingInUser = customUserService.getUserByUsername(user.getUsername());
		UserModel defaultUser = new UserModel();
		if (loggingInUser.getUsername() == defaultUser.getUsername()) {
			// add attribute title as "Login Form"
			model.addAttribute("title", "Login Form");
			// add attribute user as a default UserModel for login form to use
			model.addAttribute("user", new UserModel());
			// return login view
			return "login";
		}
		else {
			model.addAttribute("user", loggingInUser);
			model.addAttribute("userId", loggingInUser.getId());
			
			// Initialize list of retrieved CategoryModels using the findAll method of category service
			List<ItemModel> itemsArr = itemService.findAll();
			
			// add attribute categories
			model.addAttribute("itemsArr", itemsArr);
			
			// add attribute title as "Main Page"
			model.addAttribute("title", "Main Page");
			
			// return the main view with added attributes
			return "main";
		}
	}
	
	/**
	 * Displays the category page
	 * @param model
	 * @param user
	 * @return category
	 */
	@PostMapping("/backToDisplay")
	public String backToDisplay(int userId, Model model) {
		model.addAttribute("userId", userId);
		
		// Initialize list of retrieved CategoryModels using the findAll method of category service
		List<ItemModel> itemsArr = itemService.findAll();
		
		// add attribute categories
		model.addAttribute("itemsArr", itemsArr);
		
		// add attribute title as "Main Page"
		model.addAttribute("title", "Main Page");
		
		// return the main view with added attributes
		return "main";
	}
	
	/**
	 * Sends user to the Add Item Page
	 * @param userId
	 * @param model
	 * @return addItem
	 */
	@PostMapping("/addItemPage")
	public String addItemPage(int userId, Model model) {
		// set new user model equal to the user found in the DB from ID
		UserModel user = userService.findById(userId);

		// create a default ItemModel for form to use on addItem page
		ItemModel defaultItem = new ItemModel();
		// add attribute item as created default ItemModel
		model.addAttribute("item", defaultItem);
		// add page title
		model.addAttribute("title", "Add Item");
		// add attribute categoryId as the supplied categoryId parameter to continue
		// passing categoryId through pages
		model.addAttribute("userId", userId);

		model.addAttribute("user", user);

		// return addItem page with added attributes
		return "addItem";
	}

	/**
	 * Function that is called when the user clicks the add item button
	 * @param itemModel
	 * @param userId
	 * @param categoryId
	 * @param model
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/addItem")
	public String addItem(ItemModel itemModel, int userId, Model model, BindingResult bindingResult) {
		// set new user model equal to the user found in the DB from ID
		UserModel user = userService.findById(userId);
		// if the itemBusinessService create function successfully creates the supplied
		// itemModel in database the following if condition will be true
		itemModel.setUserId(userId);
		if (itemService.register(itemModel) == 1) {
			model.addAttribute("itemsArr", itemService.findAll());
			model.addAttribute("user", user);
			model.addAttribute("userId", user.getId());
		
			// return item view with added attributes
			return "main";
		}
		// else the itemBusinessService failed to create the supplied itemModel
		else {
			// add a reject value to the binding result
			bindingResult.rejectValue("name", "Invalid Details", "Incorrect Details");
			// add a default item back into view
			model.addAttribute("item", itemModel);
			// Add attribute of user so category page can check role of user.
			model.addAttribute("user", user);
			// return addItem view
			return "addItem";
		}
	}


	/**
	 * Function that is called when the user clicks the add item button
	 * @param itemModel
	 * @param categoryId
	 * @param itemId
	 * @param userId
	 * @param model
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/editItemPage")
	public String editItemPage(ItemModel itemModel, int userId, int itemId, Model model,
			BindingResult bindingResult) {
		// set new user model equal to the user found in the DB from ID
		UserModel user = userService.findById(userId);
		// set new item model equal to the item found in the DB from ID
		ItemModel item = itemService.findById(itemId);
		// add attribute item as created default ItemModel
		model.addAttribute("item", item);
		// add page title
		model.addAttribute("title", "Edit Item");
		// add attribute categoryId as the supplied categoryId parameter to continue
		// passing categoryId through pages
		model.addAttribute("userId", userId);
		// Add attribute of user so editItem page can check role of user.
		model.addAttribute("user", user);

		// return addItem page with added attributes
		return "editItem";
	}


	/**
	 * Function that is called when the user submits the edit form.
	 * @param itemModel
	 * @param categoryId
	 * @param userId
	 * @param model
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/editItem")
	public String editItem(ItemModel itemModel, int userId, Model model, BindingResult bindingResult) {
		// set new user model equal to the user found in the DB from ID
		UserModel user = userService.findById(userId);
		// if the itemBusinessService update function successfully updates the supplied
		// itemModel in database the following if condition will be true
		if (itemService.edit(itemModel) == 1) {
			// add attribute items as the list of items returned using the
			// itemBusinessService findAllCategoryItemsById method with supplied categoryId
			model.addAttribute("itemsArr", itemService.findAll());
			// Add attribute of the current item model being edited
			model.addAttribute("item", itemModel);
			// Add attribute of user so category page can check role of user.
			model.addAttribute("userId", userId);
			// return category view with added attributes
			return "main";
		}
		// else the itemBusinessService failed to create the supplied itemModel
		else {
			// add a reject value to the binding result
			bindingResult.rejectValue("name", "Invalid Details", "Incorrect Details");
			// add a default item back into view
			model.addAttribute("item", itemModel);
			// Add attribute of user so category page can check role of user.
			model.addAttribute("user", user);
			// return addItem view
			return "editItem";
		}
	}


	/**
	 * Function that is called when the admin clicks the delete item button
	 * @param itemModel
	 * @param categoryId
	 * @param itemId
	 * @param userId
	 * @param model
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/deleteItem")
	public String deleteItem(ItemModel itemModel, int userId, int itemId, Model model,
			BindingResult bindingResult) {
		// set new item model equal to the item found in the DB from ID
		ItemModel item = itemService.findById(itemId);
		// if the itemBusinessService delete function successfully deletes the supplied
		// itemModel in database the following if condition will be true
		if (itemService.remove(item) == 1) {
			System.out.println("Delete Successful");
			// add attribute items as the list of items returned using the
			// itemBusinessService findAllCategoryItemsById method with supplied categoryId
			model.addAttribute("itemsArr", itemService.findAll());
			// Add attribute of the current item model being deleted
			model.addAttribute("item", item);
			// Add attribute of user so category page can check role of user.
			model.addAttribute("userId", userId);

			// return category view with added attributes
			return "main";
		}
		// else the itemBusinessService failed to create the supplied itemModel
		else {
			// add a reject value to the binding result
			bindingResult.rejectValue("name", "Invalid Details", "Incorrect Details");
			System.out.println("Failed to delete......");
			// add attribute items as the list of items returned using the
			// itemBusinessService findAllCategoryItemsById method with supplied categoryId
			model.addAttribute("itemsArr", itemService.findAll());
			model.addAttribute("userId", userId);
			// Add attribute of the current item model being deleted
			model.addAttribute("item", item);
			// return addItem view
			return "main";
		}
		
	}
	
	/**
	 * Function that is called when a user completes an item
	 * @param userId
	 * @param itemId
	 * @param model
	 * @return
	 */
	@PostMapping("/completeItem")
	public String completeItem(int userId, int itemId, Model model) {
		
		int completeStatus = customItemService.complete(itemId);
		
		model.addAttribute("userId", userId);
		
		// Initialize list of retrieved CategoryModels using the findAll method of category service
		List<ItemModel> itemsArr = itemService.findAll();
		
		// add attribute categories
		model.addAttribute("itemsArr", itemsArr);
		
		// add attribute title as "Main Page"
		model.addAttribute("title", "Main Page");
		
		// return the main view with added attributes
		return "main";
	}
}