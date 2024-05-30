package model;

import java.util.Date;

/**
 * Title        : SavingAccount.java
 * Description  : This class represents a savings account entity in the system. It extends the Account class and includes properties
 *                such as interest rate. It provides a user-defined constructor to initialize the SavingAccount object with specified
 *                attributes. This class inherits getters and setters from the Account class for properties such as type, user, account name,
 *                create time, and balance, and provides additional getters and setters for the interest rate property. It also overrides
 *                the toString method to provide a string representation of the SavingAccount object.
 * @version     : 1.0
 */
public class SavingAccount extends Account {
    private double interestRate;

    // User-defined constructor
    public SavingAccount(String type, User user, String accountName, Date createTime, double balance, double interestRate) {
        super(type, user, accountName, createTime, balance);
        this.interestRate = interestRate;
    }

    // Getters and setters
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // String representation
    @Override
    public String toString() {
        return super.toString();
    }
}
