package atmsilmulation.view;

import atmsilmulation.services.FundTransferServices;
import atmsilmulation.services.FundTransferServicesImpl;

import java.util.Scanner;

public class FundTransferSummaryScreen {

	public void show() {
		WelcomeScreen welcomeScreen = new WelcomeScreen();
		FundTransferServices fundTransferServices = new FundTransferServicesImpl();
		TransactionScreen transactionScreen = new TransactionScreen();
		System.out.println();
		System.out.println("Fund Transfer Summary Screen");
		System.out.println("----------------------");
		
		System.out.println("Destination Account: " + FundTransferScreen1.destinationAcc);
		System.out.println("Transfer Amount: $" + FundTransferScreen1.tranferAmt);
		System.out.println("Reference Number: " + FundTransferScreen1.referenceNum);
		System.out.println("Balance: $" + WelcomeScreen.balance);
		
		System.out.println("1. Transaction");
		System.out.println("2. Exit");
		
		Scanner scan = new Scanner(System.in);
		String opt = scan.nextLine();

		switch (opt) {
		case "1":
			fundTransferServices.transaction(transactionScreen);
			break;
		case "2":
			break;
		case "":
			break;
		default:
			show();
			break;
		}
	}
}
