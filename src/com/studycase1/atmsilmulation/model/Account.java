package com.studycase1.atmsilmulation.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

		private String Name;
		private String PIN;
		private String Balance;
		private String accountNumber;
		

		
		public  Account(String name, String pIN, String balance, String accountNumber) {
			super();
			Name = name;
			PIN = pIN;
			Balance = balance;
			this.accountNumber = accountNumber;
		}

		public Account() {
			
		}
		
		private static List<Account> users = null; 
		  
	    // Static method to create instance of Singleton class 
	    public static List<Account> getInstance() 
	    { 
	        if (users == null) 
	        	users = new ArrayList<>(); 
	  
	        return users; 
	    } 


		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getPIN() {
			return PIN;
		}

		public void setPIN(String pIN) {
			PIN = pIN;
		}

		public String getBalance() {
			return Balance;
		}

		public void setBalance(String balance) {
			Balance = balance;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}
		
		
}
