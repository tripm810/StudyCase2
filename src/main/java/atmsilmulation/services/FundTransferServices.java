package atmsilmulation.services;

import atmsilmulation.model.Account;
import atmsilmulation.view.FundTransferScreen1;
import atmsilmulation.view.TransactionScreen;

import java.util.List;

public interface FundTransferServices {

    // Fund Transfer Screen

    void inputDestinationAccountScreen();

    void inputAmountScreen();

    void inputReferenceNumberScreen();

    void transferConfirmScreen(FundTransferScreen1 fundTransferScreen, List<Account> users);

    void updateCSV(List<Account> users);

    //Fund Transfer Summary Screen
    void reduceBalance();

    void transaction(TransactionScreen transactionScreen);

    boolean isExists(List<Account> users);

    void addAmountToDestination(List<Account> users);

    String submitFundTransaction(String accountNumber, String pin, String destination, int amount, String ref);
}
