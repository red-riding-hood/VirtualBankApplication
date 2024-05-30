package model;

import java.util.Date;

/**
 * Title        : Kid.java
 * Description  : This class represents a kid entity in the system. It extends the User class and inherits properties such as type,
 *                name, password, gender, date of birth, contact information, and address. It provides a user-defined constructor
 *                to initialize the Kid object with these attributes. This class also overrides the toString method to provide a
 *                string representation of the Kid object.
 * @version     : 1.0
 */
public class Kid extends User {

    // User-defined constructor
    public Kid(String type, String name, String password, String gender, Date dob, String contact, String address) {
        super(type, name, password, gender, dob, contact, address);
    }

    // String representation
    @Override
    public String toString() {
        return super.toString();
    }
}
