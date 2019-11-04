package atmsilmulation.services;

import atmsilmulation.model.Account;
import atmsilmulation.view.WelcomeScreen;

import java.util.List;

public interface UserServices {

	// Welcome Screen
	public Account validate(String accountNumber, String pin);

	public void validateAccountNumber(String accountNumber, List<Account> users, WelcomeScreen welcomeScreen);

	public void validatePinNumber(String pin, List<Account> users, WelcomeScreen welcomeScreen);
	
	public void clearUserData();

}
