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
		for (Account user : users) {
			if (WelcomeScreen.accNumberStatic.equals(user.getAccountNumber())
					&& WelcomeScreen.pinStatic.equals(user.getPIN()) && Integer.parseInt(user.getBalance()) >= amount) {
				
				TransactionHistory transactionHistory = new TransactionHistoryImpl();
				
				int temp = Integer.parseInt(user.getBalance());
				user.setBalance(String.valueOf(temp - amount));
				WelcomeScreen.balance = Integer.parseInt(user.getBalance());
				WithdrawScreen.withdrawAmount = "$" + amount;
				transactionHistory.addWithdrawHistory();
				summaryScreen.show();
			} else if (Integer.parseInt(user.getBalance()) < amount) {
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
			for (Account user : users) {
				if (WelcomeScreen.accNumberStatic.equals(user.getAccountNumber())
						&& WelcomeScreen.pinStatic.equals(user.getPIN())) {
					
					TransactionHistory transactionHistory = new TransactionHistoryImpl();
					user.setBalance(String.valueOf(WelcomeScreen.balance - Integer.parseInt(amount)));
					WelcomeScreen.balance -= Integer.parseInt(amount);
					WithdrawScreen.withdrawAmount = "$" + amount;
					transactionHistory.addWithdrawHistory();
					summaryScreen.show();
				}
			}
		}

	}
}
