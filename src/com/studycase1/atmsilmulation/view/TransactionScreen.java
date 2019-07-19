package com.studycase1.atmsilmulation.view;

import java.util.Scanner;


public class TransactionScreen {

	public void show() {


		WithdrawScreen withdrawScreen = new WithdrawScreen();
		WelcomeScreen welcomeScreen = new WelcomeScreen();
		FundTransferScreen1 fundTransferScreen = new FundTransferScreen1();
		TransactionHistoryScreen historyScreen = new TransactionHistoryScreen();
		
		
		System.out.println();
		System.out.println("Transaction Screen");
		System.out.println("----------------");

		System.out.println("1. Withdraw ");
		System.out.println("2. Fund Transfer");
		System.out.println("3. Exit ");
		System.out.println("4. Show Transaction History");
		
		Scanner scan = new Scanner(System.in);
		String opt = scan.nextLine();

		switch (opt) {

		case "1":
			withdrawScreen.show();
			break;
		case "2":
			fundTransferScreen.show();
			break;
		case "3":
			System.out.println(opt);
			
			opt = null;
			break;
			
		case "4":
			System.out.println(opt);
			historyScreen.show();
			show();
			opt = null;
			break;
		case "":
			welcomeScreen.show();
			break;
		default:
			show();
		}

	}

}
