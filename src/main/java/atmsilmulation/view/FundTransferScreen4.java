package atmsilmulation.view;

import atmsilmulation.model.Account;
import atmsilmulation.services.FundTransferServices;
import atmsilmulation.services.FundTransferServicesImpl;

import java.util.List;

public class FundTransferScreen4 {
	
	public void show() {
		
		FundTransferServices fundTransferServices = new FundTransferServicesImpl();
		FundTransferScreen1 fundTransferScreen = new FundTransferScreen1();
		List<Account> users = Account.getInstance();
		
		System.out.println();
		System.out.println("Transfer Confirmation");
		System.out.println("Destination Account: " + FundTransferScreen1.destinationAcc);
		System.out.println("Transfer Amount: $" + FundTransferScreen1.tranferAmt);
		System.out.println("Reference Number: " + FundTransferScreen1.referenceNum);

		System.out.println("1. Confirm Trx");
		System.out.println("2. Cancel Trx");
		
		fundTransferServices.transferConfirmScreen(fundTransferScreen, users);
	}

}
