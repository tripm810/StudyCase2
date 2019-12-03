package atmsimulation.services;

import atmsimulation.model.Account;

import java.util.List;

public interface UserServices {

	// Welcome Screen
	Account validate(String accountNumber, String pin);

	Account findAccountByAccountNumber(String accountNumber);

	List<Account> findAll();

}
