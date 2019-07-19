package com.studycase1.atmsilmulation.view;

import com.studycase1.atmsilmulation.services.TransactionHistory;
import com.studycase1.atmsilmulation.services.TransactionHistoryImpl;

public class TransactionHistoryScreen {
 
	public void show() {
		
		TransactionHistory transactionHistory = new TransactionHistoryImpl();
		
		System.out.println("Transaction History Screen");
		System.out.println("---------------------------");
		
		transactionHistory.readWithdrawHistoryCSV();
		
		transactionHistory.readTransferHistoryCSV();
	}
}
