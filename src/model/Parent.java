package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Title        : Parent.java
 * Description  : This class represents a parent entity in the system. It extends the User class and includes properties such as a list of kids
 *                and the parent's role. It provides a user-defined constructor to initialize the Parent object with specified attributes.
 *                This class inherits getters and setters from the User class for properties such as type, name, password, gender, date of birth,
 *                contact information, and address, and provides additional methods for managing the list of kids and for retrieving the role.
 *                It also overrides the toString method to provide a string representation of the Parent object, including the role.
 * @version     : 1.0
 */
public class Parent extends User {
    private List<User> kidList;
    private String role;

    // User-defined constructor
    public Parent(String type, String name, String password, String gender, Date dob, String contact, String address, String role) {
        super(type, name, password, gender, dob, contact, address);
        this.kidList = new ArrayList<>();
        this.role = role;
    }

    // Getters and setters
    public void addKid(User kid) {
        if (!kidList.contains(kid)) {
            kidList.add(kid);
        }
    }

    public List<User> getKidList() {
        return kidList;
    }

    // String representation
    @Override
    public String toString() {
        return super.toString() + " " + role;
    }
}
