package com.regesta.exercise.regestamarket.utils;

import com.regesta.exercise.regestamarket.model.dto.MarketUser;
import com.regesta.exercise.regestamarket.model.entity.User;

public class TestUtils {
	
	public static User getDefaultUser() {
		User user = new User();
	    user.setMail("simoneardesi@outlook.it");
	    user.setPassword("password");
	    return user;
	}
	
	public static MarketUser getDefaultMarketUser() {
		MarketUser user = new MarketUser();
	    user.setMail("simoneardesi@outlook.it");
	    user.setPassword("password");
	    return user;
	}

}
