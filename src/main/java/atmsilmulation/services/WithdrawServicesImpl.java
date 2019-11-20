package atmsilmulation.services;

import atmsilmulation.exception.WithdrawException;
import atmsilmulation.model.Account;
import atmsilmulation.model.History;
import atmsilmulation.repository.AccountRepository;
import atmsilmulation.repository.HistoryRepository;
import atmsilmulation.utils.Constant;
import atmsilmulation.utils.TransactionType;
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
            saveToHistory(account, amount, formatter);
            return true;
        }
        return false;
    }

    private void saveToHistory(Account account, int amount, DateTimeFormatter formatter) {
        History history = new History();
        history.setAccNumber(account.getAccountNumber());
        history.setBalance(account.getBalance());
        history.setAmount(String.valueOf(amount));
        history.setDate(LocalDateTime.now().format(formatter));
        history.setType(TransactionType.WITHDRAW);
        historyRepository.save(history);
        accountRepository.save(account);
    }

    @Override
    public String validateWithdrawAmount(String balance, String amount) throws WithdrawException {
        String regex = "[0-9]+";
        if (!amount.matches(regex) || amount.equals("") || amount.isEmpty()) {
            throw new WithdrawException(Constant.ONLY_NUMBER_ALLOWED);
        } else if (Integer.parseInt(amount) % 10 != 0) {
            throw new WithdrawException(Constant.INVALID_AMOUNT);
        } else if (Integer.parseInt(amount) > 1000) {
            throw new WithdrawException(Constant.MAXIMUM_WITHDRAW_AMOUNT);
        } else if (Integer.parseInt(balance) - Integer.parseInt(amount) < 0) {
            throw new WithdrawException(Constant.INSUFFICIENT_BALANCE + amount);
        }
        return null;
    }
}
