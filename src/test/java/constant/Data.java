package constant;

import atmsimulation.model.Account;

public class Data {
    public static final String DATA_ACC_NO1 = "159509";
    public static final String DATA_NAME_NO1 = "Hazard";
    public static final String DATA_PIN_NO1 = "112233";
    public static final String DATA_BALANCE_NO1 = "50";

    public static final String DATA_ACC_NO2 = "363068";
    public static final String DATA_NAME_NO2 = "Cole";
    public static final String DATA_PIN_NO2 = "112233";
    public static final String DATA_BALANCE_NO2 = "120";

    public static final String DATA_ACC_NO3 = "111111";

    public static final String MSG_CHOOSE_WITHDRAW_AMOUNT = "Please choose withdraw amount";
    public static final String MSG_SUCCESS = "SUCCESS";
    public static final String MSG_ACCOUNT_INVALID = "Account is invalid";
    public static final String MSG_INSUFFICIENT_BALANCE = "Insufficient balance $";
    public static final String MSG_MAXIMUM_TRANSFER_AMOUNT = "Maximum amount to transfer is $1000";
    public static final String MSG_ONLY_NUMBER_ALLOWED = "Only Number Allowed";
    public static final String MSG_INVALID_AMOUNT = "Invalid amount";
    public static final String MSG_MAXIMUM_WITHDRAW_AMOUNT = "Maximum amount to withdraw is $1000";

    public static final Account account = new Account("Hazard", "159509", "112233", "50");
    public static final Account accountDontHaveMoney = new Account("Hazard", "159509", "112233", "0");
    public static final Account destinationAccount = new Account("Cole", "363068", "112233", "120");
}
