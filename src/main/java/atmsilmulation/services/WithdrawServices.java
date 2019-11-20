package atmsilmulation.services;

import atmsilmulation.exception.WithdrawException;
import atmsilmulation.model.Account;


public interface WithdrawServices {

	// Withdraw Screen
	public boolean calculateWithdrawAmount(Account user, int amount);
	// Other Withdraw Screen

	public String validateWithdrawAmount(String balance, String amount) throws WithdrawException;

}
