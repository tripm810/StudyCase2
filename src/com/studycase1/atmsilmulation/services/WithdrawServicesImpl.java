package com.studycase1.atmsilmulation.services;

import java.util.List;

import com.studycase1.atmsilmulation.model.Account;
import com.studycase1.atmsilmulation.view.SummaryScreen;
import com.studycase1.atmsilmulation.view.TransactionScreen;
import com.studycase1.atmsilmulation.view.WelcomeScreen;
import com.studycase1.atmsilmulation.view.WithdrawScreen;

public class WithdrawServicesImpl implements WithdrawServices {

	@Override
	public void caculateWithdrawAmount(List<Account> users, String accountNumber, String pin,
			SummaryScreen summaryScreen, TransactionScreen transactionScreen, int amount) {
		for (int i = 0; i < users.size(); i++) {
			if (WelcomeScreen.accNumberStatic.equals(users.get(i).getAccountNumber())
					&& WelcomeScreen.pinStatic.equals(users.get(i).getPIN()) && Integer.parseInt(users.get(i).getBalance()) >= amount) {
				
				TransactionHistory transactionHistory = new TransactionHistoryImpl();
				
				int temp = Integer.parseInt(users.get(i).getBalance());
				users.get(i).setBalance(String.valueOf(temp - amount));
				WelcomeScreen.balance = Integer.parseInt(users.get(i).getBalance());
				WithdrawScreen.withdrawAmount = "$" + amount;
				transactionHistory.addWithdrawHistory();
				summaryScreen.show();
			} else if (Integer.parseInt(users.get(i).getBalance()) < amount) {
				System.out.println("Insufficient balance $" + amount);
				transactionScreen.show();
			}
		}

	}

	@Override
	public void validateAndCalculateWithdrawAmount(String amount, List<Account> users,
			WithdrawScreen withdrawScreen, SummaryScreen summaryScreen) {
		String regex = "[0-9]+";
		if (!amount.matches(regex)) {
			System.out.println("Only Number Allowed");
			withdrawScreen.show();
		} else if (Integer.parseInt(amount) % 10 != 0) {
			System.out.println("Invalid ammount");
			withdrawScreen.show();
		} else if (Integer.parseInt(amount) > 1000) {
			System.out.println("Maximum amount to withdraw is $1000");
			withdrawScreen.show();
		} else if (WelcomeScreen.balance - Integer.parseInt(amount) < 0) {
			System.out.println("Insufficient balance $" + amount);
			withdrawScreen.show();
		} else {
			// calculate and set balance
			for (int i = 0; i < users.size(); i++) {
				if (WelcomeScreen.accNumberStatic.equals(users.get(i).getAccountNumber())
						&& WelcomeScreen.pinStatic.equals(users.get(i).getPIN())) {
					
					TransactionHistory transactionHistory = new TransactionHistoryImpl();
					users.get(i).setBalance(String.valueOf(WelcomeScreen.balance - Integer.parseInt(amount)));
					WelcomeScreen.balance -= Integer.parseInt(amount);
					WithdrawScreen.withdrawAmount = "$" + amount;
					transactionHistory.addWithdrawHistory();
					summaryScreen.show();
				}
			}
		}

	}
}
