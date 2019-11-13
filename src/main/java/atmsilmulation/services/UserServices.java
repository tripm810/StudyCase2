package atmsilmulation.services;

import atmsilmulation.model.Account;

public interface UserServices {

	// Welcome Screen
	Account validate(String accountNumber, String pin);

}
