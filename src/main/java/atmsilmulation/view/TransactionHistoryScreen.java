package atmsilmulation.view;

import atmsilmulation.services.TransactionHistory;
import atmsilmulation.services.TransactionHistoryImpl;

public class TransactionHistoryScreen {
 
	public void show() {
		
		TransactionHistory transactionHistory = new TransactionHistoryImpl();
		
		System.out.println();
		System.out.println("Transaction History Screen");
		System.out.println("---------------------------");
		
		transactionHistory.readWithdrawHistoryCSV();
		
		transactionHistory.readTransferHistoryCSV();
	}
}
