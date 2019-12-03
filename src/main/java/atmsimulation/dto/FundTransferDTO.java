package atmsimulation.dto;

public class FundTransferDTO {

    String accountDestination;
    String amount;
    String ref;

    public FundTransferDTO() {
    }

    public FundTransferDTO(String accountDestination, String amount, String ref) {
        this.accountDestination = accountDestination;
        this.amount = amount;
        this.ref = ref;
    }

    public String getAccountDestination() {
        return accountDestination;
    }

    public void setAccountDestination(String accountDestination) {
        this.accountDestination = accountDestination;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
