package atmsilmulation.services;

import atmsilmulation.exception.FundTransactionException;
import atmsilmulation.model.Account;

import java.util.List;

public interface FundTransferServices {

    String submitFundTransaction(String accountNumber, String pin, String destination, int amount, String ref) throws FundTransactionException;
}
