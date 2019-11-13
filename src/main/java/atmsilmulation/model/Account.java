package atmsilmulation.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private String pin;

    @Column
    private String balance;

    @Column
    private String accountNumber;


    public Account(String name, String pIN, String balance, String accountNumber) {
        super();
        this.name = name;
        pin = pIN;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public Account() {

    }

    private static List<Account> users = null;

    // Static method to create instance of Singleton class
    public static List<Account> getInstance() {
        if (users == null)
            users = new ArrayList<>();

        return users;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pIN) {
        pin = pIN;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


}
