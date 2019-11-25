package atmsimulation.services;

import atmsimulation.model.Account;

public interface UserServices {

	// Welcome Screen
	Account validate(String accountNumber, String pin);

	Account findAccountByAccountNumber(String accountNumber);

}
