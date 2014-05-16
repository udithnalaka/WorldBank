package com.ud.mobile.worldbank.util;

import java.util.ArrayList;
import java.util.List;

public class AccountUtil {
	
	public static List<String> retrieveAccountDetails(String accountName){
		
		List<String> accountDetails = new ArrayList<String>();
		
		if(accountName.trim().equals("Smart Account")){
			accountDetails.add(0, "1000.00");
			accountDetails.add(1, "4%");
		}else if(accountName.trim().equals("Net Saver")){
			accountDetails.add(0, "2000.00");
			accountDetails.add(1, "5%");
		}else if(accountName.trim().equals("Credit Card")){
			accountDetails.add(0, "3000.00");
			accountDetails.add(1, "20%");			
		}
		
		return accountDetails;
	}
	
	public static List<String> getAllAccounts(){
		List<String> accounts = new ArrayList<String>();
		
		accounts.add(" ");
		accounts.add("Smart Account - 106873");
		accounts.add("Net Saver - 245783");
		accounts.add("Credit Card - 123456");
		
		return accounts;
	}

}
