package com.studycase1.atmsilmulation.view;

import com.studycase1.atmsilmulation.services.FundTransferServices;
import com.studycase1.atmsilmulation.services.FundTransferServicesImpl;

public class FundTransferScreen1 {
	public static String destinationAcc;
	public static String tranferAmt;
	public static String referenceNum;

	FundTransferServices fundTransferServices = new FundTransferServicesImpl();

	public void show() {

		System.out.println();
		System.out.println("Fund Transfer Screen");
		System.out.println("----------------");
		System.out.println();
		System.out.print("Please enter destination account and \r\n" + 
				         "Press enter to continue or \r\n" + 
				         "Press cancel (Esc) to go back to Transaction: ");
		
		fundTransferServices.inputDestinationAccountScreen();
		
	}

}
