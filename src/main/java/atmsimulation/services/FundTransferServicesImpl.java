package atmsimulation.services;

import atmsimulation.exception.FundTransactionException;
import atmsimulation.model.Account;
import atmsimulation.model.History;
import atmsimulation.repository.AccountRepository;
import atmsimulation.repository.HistoryRepository;
import atmsimulation.utils.Constant;
import atmsimulation.utils.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class FundTransferServicesImpl implements FundTransferServices {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Override
    public String submitFundTransaction(String accountNumber, String pin, String destination, int amount, String ref) throws FundTransactionException {
        Account currentAccount = accountRepository.findAccountByAccountNumberAndPin(accountNumber, pin);
        Account destinationAccount = accountRepository.findAccountByAccountNumber(destination);


		if (currentAccount == null || destinationAccount == null) {
			throw new FundTransactionException(Constant.ACCOUNT_INVALID);
		}
		if (Integer.parseInt(currentAccount.getBalance()) < amount) {
			throw new FundTransactionException(Constant.INSUFFICIENT_BALANCE + amount);
		}
		if (amount > 1000) {
			throw new FundTransactionException(Constant.MAXIMUM_TRANSFER_AMOUNT);
		}

		currentAccount.setBalance(Integer.toString(Integer.parseInt(currentAccount.getBalance()) - amount));
        destinationAccount.setBalance(Integer.toString(Integer.parseInt(destinationAccount.getBalance()) + amount));

        accountRepository.save(currentAccount);

        accountRepository.save(destinationAccount);

		createNewHistory(amount, ref, currentAccount, destinationAccount);
		return Constant.SUCCESS;
    }

	private void createNewHistory(int amount, String ref, Account currentAccount, Account destinationAccount) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		History history = new History();
		history.setAccNumber(currentAccount.getAccountNumber());
		history.setBalance(currentAccount.getBalance());
		history.setAmount(String.valueOf(amount));
		history.setRefNumber(ref);
		history.setDestinationAcc(destinationAccount.getAccountNumber());
		history.setDate(LocalDateTime.now().format(formatter));
		history.setType(TransactionType.FUND_TRANSFER);
		historyRepository.save(history);
	}

}
