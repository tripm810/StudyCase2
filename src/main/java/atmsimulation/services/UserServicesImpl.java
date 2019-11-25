package atmsimulation.services;

import atmsimulation.model.Account;
import atmsimulation.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServicesImpl implements UserServices {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account validate(String accountNumber, String pin) {
		return accountRepository.findAccountByAccountNumberAndPin(accountNumber,pin);
	}

	@Override
	public Account findAccountByAccountNumber(String accountNumber) {
		return accountRepository.findAccountByAccountNumber(accountNumber);
	}

}
