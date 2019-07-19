package com.studycase1.atmsilmulation.services;

import com.studycase1.atmsilmulation.model.History;

public interface TransactionHistory {
	
	public void addWithdrawHistory();
	
	public void addFundTransferHistory();
	
	public void readWithdrawHistoryCSV();
	
	public void printWithdrawHistory(History history);
	
	public void readTransferHistoryCSV();
	
	public void printTransferHistory(History history);
	
	
}
