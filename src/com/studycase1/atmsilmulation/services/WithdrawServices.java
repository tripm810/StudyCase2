package com.studycase1.atmsilmulation.services;

import java.util.List;

import com.studycase1.atmsilmulation.model.Account;
import com.studycase1.atmsilmulation.view.SummaryScreen;
import com.studycase1.atmsilmulation.view.TransactionScreen;
import com.studycase1.atmsilmulation.view.WithdrawScreen;

public interface WithdrawServices {

	// Withdraw Screen
	public void caculateWithdrawAmount(List<Account> users, String accountNumber, String pin,
			SummaryScreen summaryScreen, TransactionScreen transactionScreen, int amount);
	// Other Withdraw Screen

	public void validateAndCalculateWithdrawAmount(String amount, List<Account> users,
			WithdrawScreen withdrawScreen,SummaryScreen summaryScreen);

}
