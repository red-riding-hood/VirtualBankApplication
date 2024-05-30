package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title        : Account.java
 * Description  : This class represents an account entity in the system. It includes properties such as the account type, user associated
 *                with the account, account name, creation time, and balance. It provides a user-defined constructor to initialize the
 *                Account object with these attributes. Getters and setters are provided for each property to allow for their retrieval
 *                and modification. The class also overrides the toString method to provide a string representation of the Account object,
 *                including formatted date and time.
 * @version     : 1.0
 */
public class Account {
    private String type;
    private User user;
    private String accountName;
    private Date createTime;
    private double balance;

    // User-defined class
    public Account(String type, User user, String accountName, Date createTime, double balance) {
        this.type = type;
        this.user = user;
        this.accountName = accountName;
        this.balance = balance;
        this.createTime = createTime;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    // String representation
    @Override
    public String toString() {
        SimpleDateFormat dateTimeSdf = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
        return type + " " + user.getName() + " " + accountName + " " + dateTimeSdf.format(createTime) + " " + balance;
    }


}
