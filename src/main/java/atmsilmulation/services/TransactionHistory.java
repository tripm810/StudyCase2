package atmsilmulation.services;


import atmsilmulation.model.History;

import java.util.List;

public interface TransactionHistory {
	
	 void addWithdrawHistory();
	
	 void addFundTransferHistory();
	
	 void readWithdrawHistoryCSV();
	
	 void printWithdrawHistory(History history);

	 void readTransferHistoryCSV();
	
	 void printTransferHistory(History history);

	List<History> findByAccNumber (String accountNumber);
	
	
}
