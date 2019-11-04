package atmsilmulation.view;

import atmsilmulation.model.Account;
import atmsilmulation.services.UserServices;
import atmsilmulation.services.UserServicesImpl;

import java.util.List;
import java.util.Scanner;

public class WelcomeScreen {

	public static String accNumberStatic;
	public static String pinStatic;
	public static int balance;

	public void show() {

		
		UserServices userService = new UserServicesImpl();
		List<Account> users = Account.getInstance();
		WelcomeScreen welcomeScreen = new WelcomeScreen();
		Scanner scan = new Scanner(System.in);

		userService.clearUserData();
		
		System.out.println();
		System.out.println("Welcome Screen");
		System.out.println("---------------");

		System.out.print("Enter Account Number: ");
		
		String accountNumber = scan.nextLine();
		String acc = accountNumber;
		accNumberStatic = acc;

		userService.validateAccountNumber(accNumberStatic, users, welcomeScreen);

		System.out.print("Enter PIN: ");
		String pin = scan.nextLine();
		pinStatic = pin;
		
		userService.validatePinNumber(pinStatic, users, welcomeScreen);

		userService.validate(accNumberStatic, pinStatic);

	}

}
