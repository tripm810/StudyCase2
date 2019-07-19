package com.studycase1.atmsilmulation.services;

import java.util.List;

import com.studycase1.atmsilmulation.model.Account;
import com.studycase1.atmsilmulation.view.WelcomeScreen;

public interface UserService {

	// Welcome Screen
	public void validate(List<Account> users, String accountNumber, String pin);

	public void validateAccountNumber(String accountNumber, List<Account> users, WelcomeScreen welcomeScreen);

	public void validatePinNumber(String pin, List<Account> users, WelcomeScreen welcomeScreen);
	
	public void clearUserData();

}
