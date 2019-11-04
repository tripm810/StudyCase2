package atmsilmulation.services;

import atmsilmulation.model.Account;
import atmsilmulation.repository.AccountRepository;
import atmsilmulation.view.WelcomeScreen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account validate(String accountNumber, String pin) {
		return accountRepository.findAccountByAccountNumberAndPin(accountNumber,pin);
	}

	@Override
	public void validateAccountNumber(String accountNumber, List<Account> users, WelcomeScreen welcomeScreen) {
		String regex = "^[0-9]+$";
		int accLength = accountNumber.length();
		if (accLength != 6) {
			System.out.println("Account Number should have 6 digits length");
			welcomeScreen.show();
		}
		if (!accountNumber.matches(regex)) {
			System.out.println("Account Number should only contains numbers");
			welcomeScreen.show();
		}
	}

	@Override
	public void validatePinNumber(String pin, List<Account> users, WelcomeScreen welcomeScreen) {
		String regex = "^[0-9]+$";
		int pinLength = pin.length();
		if (pinLength != 6) {
			System.out.println("PIN should have 6 digits length");
			welcomeScreen.show();
		}
		if (!(pin.matches(regex))) {
			System.out.println("PIN should only contains numbers");
			welcomeScreen.show();
		}
	}

	@Override
	public void clearUserData() {
		
		List<Account> users = Account.getInstance();
		WelcomeScreen.accNumberStatic = null;
		WelcomeScreen.pinStatic = null;
		
	}

}
