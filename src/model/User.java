package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title        : User.java
 * Description  : This class represents a user entity in the system. It includes properties such as type, name, password,
 *                gender, date of birth, contact information, and address. It provides getters and setters for these properties
 *                and a method for generating a string representation of the user.
 * @version     : 1.0
 */
public class User {
    private String type;
    private String name;
    private String password;
    private String gender;
    private Date dob;
    private String contact;
    private String address;

    // User-defined class
    public User(String type, String name, String password, String gender, Date dob, String contact, String address) {
        this.type = type;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.contact = contact;
        this.address = address;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // String representation
    @Override
    public String toString() {
        SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
        return type + " " + name + " " + password + " "
                + gender + " " + dateSdf.format(dob) + " " + contact + " "
                + address;
    }
}
