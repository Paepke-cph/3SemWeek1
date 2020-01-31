package entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "BankCustomer")
public class BankCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private double balance;

    private int customerRanking;
    private String internalInfo;

    public BankCustomer() {
    }

    public BankCustomer(String firstName, String lastName, String accountNumber,
                        double balance, int customerRanking, String internalInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerRanking = customerRanking;
        this.internalInfo = internalInfo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public int getCustomerRanking() {
        return customerRanking;
    }

    public String getInternalInfo() {
        return internalInfo;
    }

    public int getId() {
        return id;
    }

}
