package atmsilmulation.services;

import atmsilmulation.model.Account;
import atmsilmulation.model.History;
import atmsilmulation.repository.AccountRepository;
import atmsilmulation.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class WithdrawServicesImpl implements WithdrawServices {

	@Autowired
	HistoryRepository historyRepository;

	@Autowired
	AccountRepository accountRepository;

	@Override
	public boolean calculateWithdrawAmount(Account account, int amount) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		int accountBalance = Integer.parseInt(account.getBalance());
		if (accountBalance >= amount) {
			account.setBalance(Integer.toString(accountBalance - amount));
			History history = new History();
			history.setAccNumber(account.getAccountNumber());
			history.setBalance(account.getBalance());
			history.setAmount(String.valueOf(amount));
			history.setDate(LocalDateTime.now().format(formatter));
			history.setType("WITHDRAW");
			historyRepository.save(history);
			accountRepository.save(account);
			return true;
		}
        return false;
	}

	@Override
	public String validateWithdrawAmount(String balance, String amount) {
		String regex = "[0-9]+";
		if (!amount.matches(regex)) {
			return "Only Number Allowed";
		} else if (Integer.parseInt(amount) % 10 != 0) {
			return "Invalid amount";
		} else if (Integer.parseInt(amount) > 1000) {
			return "Maximum amount to withdraw is $1000";
		} else if (Integer.parseInt(balance) - Integer.parseInt(amount) < 0) {
			return "Insufficient balance $" + amount;
		}
		return null;
	}
}
