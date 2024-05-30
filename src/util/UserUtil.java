package util;

import model.Kid;
import model.Parent;
import model.User;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Title        : UserUtil.java
 * Description  : This class provides utility methods for loading and saving user data.
 *                It includes methods to read user data from a file and write user data to a file.
 * @version     : 1.0
 */
public class UserUtil {
    private static SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Loads a list of users from a specified file.
     *
     * The file should contain user data with each user on a separate line.
     * User data should be space-separated and follow the format:
     * Kid: Kid name password gender dateOfBirth contact address
     * Parent: Parent name password gender dateOfBirth contact address role
     *
     * @param userList the list to populate with user data
     * @param filePath the path to the file containing user data
     */
    public void loadUserList(List<User> userList, String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner file = new Scanner(fis);
            while (file.hasNextLine()) {
                String[] parts = file.nextLine().trim().split(" ");
                if (parts.length != 7 && parts.length != 8) {
                    continue;
                }
                String type = parts[0];
                String name = parts[1];
                String password = parts[2];
                String gender = parts[3];
                Date dob = dateSdf.parse(parts[4]);
                String contact = parts[5];
                String address = parts[6];
                String role = "";
                if (type.equals("Parent")) {
                    role = parts[7];
                }
                User user = null;
                if (type.equals("Kid")) {
                    user = new Kid("Kid", name, password, gender, dob, contact, address);
                    userList.add(user);
                } else if (type.equals("Parent")) {
                    user = new Parent("Parent", name, password, gender, dob, contact, address, role);
                    userList.add(user);
                }
            }
            file.close();
            fis.close();
            System.out.println("Read file " + filePath + " successfully!");
        } catch (IOException e) {
            System.out.println("File does not exist! ");
        } catch (ParseException e) {
            System.out.println("Invalid data format! ");
        }
    }

    /**
     * Saves a list of users to a specified file.
     *
     * Each user's data will be written to the file in a format appropriate
     * for later reading by loadUserList method.
     *
     * @param userList the list of users to save
     * @param filePath the path to the file to save user data
     */
    public void saveUserList(List<User> userList, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < userList.size(); i++) {
                User currentUser = userList.get(i);
                fw.write(currentUser + "\n");
            }
            fw.close();
            System.out.println("Save file " + filePath + " successfully!");
        } catch (IOException e) {
            System.out.println("File " + filePath + " does not exist! ");
        }
    }
}
