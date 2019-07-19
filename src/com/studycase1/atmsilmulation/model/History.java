package com.studycase1.atmsilmulation.model;

public class History {

	private String type;
	private String date;
	private String accNumber;
	private String withdrawAtm;
	private String balance;
	private String destinationAcc;
	private String transferAtm;
	private String refNumber;


	public History() {
		super();
	}

	public History(String type, String date, String accNumber, String withdrawAtm, String balance) {
		super();
		this.type = type;
		this.date = date;
		this.accNumber = accNumber;
		this.withdrawAtm = withdrawAtm;
		this.balance = balance;
	}

	public History(String type, String date, String accNumber, String balance, String destinationAcc,
			String transferAtm) {
		super();
		this.type = type;
		this.date = date;
		this.accNumber = accNumber;
		this.balance = balance;
		this.destinationAcc = destinationAcc;
		this.transferAtm = transferAtm;
	}
	

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getWithdrawAtm() {
		return withdrawAtm;
	}

	public void setWithdrawAtm(String withdrawAtm) {
		this.withdrawAtm = withdrawAtm;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getDestinationAcc() {
		return destinationAcc;
	}

	public void setDestinationAcc(String destinationAcc) {
		this.destinationAcc = destinationAcc;
	}

	public String getTransferAtm() {
		return transferAtm;
	}

	public void setTransferAtm(String transferAtm) {
		this.transferAtm = transferAtm;
	}

}
