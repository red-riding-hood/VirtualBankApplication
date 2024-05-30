package model;

import java.util.Date;

/**
 * Title        : CurrentAccount.java
 * Description  : This class represents a current account entity in the system. It extends the Account class and includes an additional
 *                property for the overdraft amount. It provides a user-defined constructor to initialize the CurrentAccount object with
 *                specified attributes. This class inherits getters and setters from the Account class for properties such as type, user,
 *                account name, creation time, and balance, and provides additional getters and setters for the overdraft amount property.
 *                It also overrides the toString method to provide a string representation of the CurrentAccount object.
 * @version     : 1.0
 */
public class CurrentAccount extends Account {
    private double overdraftAmount;

    // User-defined constructor
    public CurrentAccount(String type, User user, String accountName, Date createTime, double balance, double overdraftAmount) {
        super(type, user, accountName, createTime, balance);
        this.overdraftAmount = overdraftAmount;
    }

    // Getters and setters
    public double getOverdraftAmount() {
        return overdraftAmount;
    }

    public void setOverdraftAmount(double overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }

    // String representation
    @Override
    public String toString() {
        return super.toString();
    }
}
