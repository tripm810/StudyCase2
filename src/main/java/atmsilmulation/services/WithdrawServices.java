package atmsilmulation.services;

import atmsilmulation.model.Account;
import atmsilmulation.view.SummaryScreen;
import atmsilmulation.view.TransactionScreen;
import atmsilmulation.view.WithdrawScreen;

import java.util.List;


public interface WithdrawServices {

	// Withdraw Screen
	public boolean calculateWithdrawAmount(Account user, int amount);
	// Other Withdraw Screen

	public String validateWithdrawAmount(String balance, String amount);

}
