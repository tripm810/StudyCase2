package atmsimulation.services;

import atmsimulation.exception.FundTransactionException;

public interface FundTransferServices {

    String submitFundTransaction(String accountNumber, String pin, String destination, int amount, String ref) throws FundTransactionException;
}
