package com.studycase1.atmsilmulation.view;

import com.studycase1.atmsilmulation.services.FundTransferServices;
import com.studycase1.atmsilmulation.services.FundTransferServicesImpl;

public class FundTransferScreen2 {

	public void show() {
		FundTransferServices fundTransferServices = new FundTransferServicesImpl();
		
		System.out.println();
		System.out.print("Please enter transfer amount and \r\n" + 
			         	 "Press enter to continue or \r\n" + 
				         "Press cancel (Esc) to go back to Transaction: ");
		
		fundTransferServices.inputAmountScreen();
		
	}
}
