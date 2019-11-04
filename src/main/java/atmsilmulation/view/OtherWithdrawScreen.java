package atmsilmulation.view;

import atmsilmulation.model.Account;
import atmsilmulation.services.WithdrawServices;
import atmsilmulation.services.WithdrawServicesImpl;

import java.util.List;
import java.util.Scanner;

public class OtherWithdrawScreen {

	public void show() {
		List<Account> users = Account.getInstance();
		SummaryScreen summaryScreen = new SummaryScreen();
		WithdrawScreen withdrawScreen = new WithdrawScreen();
		WithdrawServices withdrawServices = new WithdrawServicesImpl();

		System.out.println();
		System.out.println("Other Withdraw Screen");
		System.out.println("--------------------");
		System.out.print("Enter amount to withdraw: ");
		Scanner scan = new Scanner(System.in);
		String amount = scan.nextLine();

		// withdrawServices.validateAndCalculateWithdrawAmount(amount, users, withdrawScreen, summaryScreen);
	}
}