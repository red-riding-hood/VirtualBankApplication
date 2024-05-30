package util;

import controller.UserController;
import model.Parent;
import model.User;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Title        : ParentKidUtil.java
 * Description  : This class provides utility methods for loading and saving parent-kid relationships.
 *                It includes methods to read parent-kid relationships from a file and write these relationships to a file.
 * @version     : 1.0
 */
public class ParentKidUtil {

    /**
     * Loads the parent-kid relationships from a specified file.
     *
     * The file should contain parent-kid relationship data with each relationship on a separate line.
     * Each line should be space-separated and follow the format:
     * parentName kidName
     *
     * @param userController the user controller to retrieve User objects by name
     * @param userList the list of users to update with parent-kid relationships
     * @param filePath the path to the file containing parent-kid relationship data
     */
    public void loadParentKidList(UserController userController, List<User> userList, String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner file = new Scanner(fis);
            while (file.hasNextLine()) {
                String[] parts = file.nextLine().trim().split(" ");
                if (parts.length != 2) {
                    continue;
                }
                User parent = userController.getUserByName(parts[0]);
                User kid = userController.getUserByName(parts[1]);
                if (parent == null || kid == null) {
                    continue;
                }
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).getType().equals("Kid") || userList.get(i) != parent) {
                        continue;
                    }
                    ((Parent) userList.get(i)).addKid(kid);
                }
            }
            file.close();
            fis.close();
            System.out.println("Read file " + filePath + " successfully!");
        } catch (IOException e) {
            System.out.println("File does not exist! ");
        }
    }

    /**
     * Saves the parent-kid relationships to a specified file.
     *
     * Each parent's kids will be written to the file in a format appropriate
     * for later reading by loadParentKidList method.
     *
     * @param userList the list of users to save parent-kid relationships from
     * @param filePath the path to the file to save parent-kid relationship data
     */
    public void saveParentKidList(List<User> userList, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < userList.size(); i++) {
                User currentUser = userList.get(i);
                if (currentUser.getType().equals("Kid")) {
                    continue;
                }
                List<User> parentKidList = ((Parent) currentUser).getKidList();
                for (User parentKid : parentKidList) {
                    fw.write(currentUser.getName() + " " + parentKid.getName() + "\n");
                }
            }
            fw.close();
            System.out.println("Save file " + filePath + " successfully!");
        } catch (IOException e) {
            System.out.println("File " + filePath + " does not exist! ");
        }
    }
}
