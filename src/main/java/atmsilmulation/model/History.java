package atmsilmulation.model;

import javax.persistence.*;

@Entity
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String type;
	@Column
	private String date;
	@Column
	private String accNumber;
	@Column
	private String amount;
	@Column
	private String balance;
	@Column
	private String destinationAcc;
	@Column
	private String refNumber;


	public History() {
		super();
	}

	public History(String type, String date, String accNumber, String amount, String balance) {
		super();
		this.type = type;
		this.date = date;
		this.accNumber = accNumber;
		this.amount = amount;
		this.balance = balance;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

}
