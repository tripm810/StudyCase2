package com.studycase1.atmsilmulation.services;

import java.util.List;

import com.studycase1.atmsilmulation.model.Account;
import com.studycase1.atmsilmulation.view.TransactionScreen;
import com.studycase1.atmsilmulation.view.WelcomeScreen;

public class UserServicesImpl implements UserService {

	@Override
	// Get balance for calculate and validate accNumber and PIN
	public void validate(List<Account> users, String accountNumber, String pin) {
		int temp = 0;
		for (int i = 0; i < users.size(); i++) {
			if (accountNumber.equals(users.get(i).getAccountNumber()) && pin.equals(users.get(i).getPIN())) {
				WelcomeScreen.balance = Integer.parseInt(users.get(i).getBalance());
				temp = 0;
				break;
			} else {
				temp = 1;
			}
		}
		if (temp == 1) {
			System.out.println("Invalid Account Number or PIN");
		}
		if (temp == 0) {
			TransactionScreen transactionScreen = new TransactionScreen();
			transactionScreen.show();
		}
	}

	@Override
	public void validateAccountNumber(String accountNumber, List<Account> users, WelcomeScreen welcomeScreen) {
		String regex = "^[0-9]+$";
		int accLength = accountNumber.length();
		if (accLength != 6) {
			System.out.println("Account Number should have 6 digits length");
			welcomeScreen.show();
		}
		if (!accountNumber.matches(regex)) {
			System.out.println("Account Number should only contains numbers");
			welcomeScreen.show();
		}
	}

	@Override
	public void validatePinNumber(String pin, List<Account> users, WelcomeScreen welcomeScreen) {
		String regex = "^[0-9]+$";
		int pinLength = pin.length();
		if (pinLength != 6) {
			System.out.println("PIN should have 6 digits length");
			welcomeScreen.show();
		}
		if (!(pin.matches(regex))) {
			System.out.println("PIN should only contains numbers");
			welcomeScreen.show();
		}
	}

	@Override
	public void clearUserData() {
		
		List<Account> users = Account.getInstance();
		WelcomeScreen.accNumberStatic = null;
		WelcomeScreen.pinStatic = null;
		
	}

}
