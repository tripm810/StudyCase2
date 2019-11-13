package atmsilmulation.services;

import atmsilmulation.model.Account;
import atmsilmulation.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account validate(String accountNumber, String pin) {
		return accountRepository.findAccountByAccountNumberAndPin(accountNumber,pin);
	}

}