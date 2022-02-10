package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.gcu.business.BusinessServiceInterface;
import com.gcu.business.ItemBusinessService;
import com.gcu.business.ItemBusinessServiceInterface;
import com.gcu.business.UserBusinessService;
import com.gcu.business.UserBusinessServiceInterface;
import com.gcu.model.ItemModel;
import com.gcu.model.UserModel;


@Configuration
public class SpringConfig {
	
	@Bean("userBusinessService")
	@SessionScope
	public BusinessServiceInterface<UserModel> getUserBusiness()
	{
		return new UserBusinessService();
	}
	
	@Bean("itemBusinessService")
	@SessionScope
	public BusinessServiceInterface<ItemModel> getItemBusiness()
	{
		return new ItemBusinessService();
	}
	
	
	//Specific Interfaces 
	@Bean("customUserBusinessService")
	@SessionScope
	public UserBusinessServiceInterface<UserModel> getCustomUserBusiness()
	{
		return new UserBusinessService();
	}

	@Bean("customItemBusinessService")
	@SessionScope
	public ItemBusinessServiceInterface<ItemModel> getCustomItemBusiness()
	{
		return new ItemBusinessService();
	}

}
