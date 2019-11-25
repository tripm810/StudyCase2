package atmsimulation.dto;

public class WithdrawDTO {

    private String accountNumber;
    private String pin;
    private String amount;
    private String balance;
    private String inputValue;
    private String date;

    public WithdrawDTO() {
    }

    public WithdrawDTO(String accountNumber, String pin, String amount, String balance, String inputValue, String date) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.amount = amount;
        this.balance = balance;
        this.inputValue = inputValue;
        this.date = date;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
