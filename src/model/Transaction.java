package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title        : Transaction.java
 * Description  : This class represents a transaction entity in the system. It includes properties such as source, destination,
 *                type, transaction amount, transaction time, and a remark. It provides getters and setters for these properties
 *                and a method for generating a string representation of the transaction.
 * @version     : 1.0
 */
public class Transaction {
    private String source;
    private String destination;
    private String type;
    private double transAmount;
    private Date transTime;
    private String remark;

    // User-defined constructor
    public Transaction(String source, String destination, String type, double transAmount, Date transTime, String remark) {
        this.source = source;
        this.destination = destination;
        this.type = type;
        this.transAmount = transAmount;
        this.transTime = transTime;
        this.remark = remark;
    }

    // Getters and setters
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(double transAmount) {
        this.transAmount = transAmount;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    // String representation
    @Override
    public String toString() {
        SimpleDateFormat dateTimeSdf = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
        return source + " " + destination + " " + type + " " + transAmount + " " + dateTimeSdf.format(transTime) + " " + remark;
    }
}
