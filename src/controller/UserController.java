package controller;

import model.Parent;
import model.User;
import util.ParentKidUtil;
import util.UserUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Title        : UserController.java
 * Description  : This class is responsible for handling operations related to users. It manages user login, addition, removal, and update
 *                of user details. The UserController also handles loading and saving user data from and to files, and filling tables
 *                with user and parent-kid information for display purposes. Additionally, it provides functionality to get specific
 *                users and lists of users or candidate kids.
 * @version     : 1.0
 */
public class UserController {
    private UserUtil userUtil = new UserUtil();
    private ParentKidUtil parentKidUtil = new ParentKidUtil();
    private List<User> userList;
    private SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");

    public UserController() {
        this.userList = new ArrayList<>();
    }

    /**
     * Authenticates a user based on the provided username, userType, and password.
     *
     * @param userName the username of the user
     * @param userType the type of the user
     * @param password the password of the user
     * @return the authenticated user if credentials match, null otherwise
     */
    public User login(String userName, String userType, String password) {
        for (int i = 0; i < userList.size(); i++) {
            User currentUser = userList.get(i);
            if (currentUser.getName().equals(userName) && currentUser.getType().equals(userType) && currentUser.getPassword().equals(password)) {
                return currentUser;
            }
        }
        return null;
    }

    /**
     * Adds a new user to the user list.
     *
     * @param user the user to be added
     */
    public void addUser(User user) {
        userList.add(user);
    }
    /**
     * Removes an existing user from the user list.
     *
     * @param user the user to be removed
     */
    public void removeUser(User user) {
        userList.remove(user);
    }
    /**
     * Updates the details of an existing user in the user list.
     *
     * @param updateUser the user with updated details
     */
    public void updateUser(User updateUser) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getType().equals(updateUser.getType()) && userList.get(i).getName().equals(updateUser.getName())) {
                userList.set(i, updateUser);
                break;
            }
        }
    }
    /**
     * Retrieves a user by their username.
     *
     * @param searchUserName the username to search for
     * @return the user if found, null otherwise
     */
    public User getUserByName(String searchUserName) {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.getName().equals(searchUserName)) {
                return user;
            }
        }
        return null;
    }
    /**
     * Retrieves a user by their type and name.
     *
     * @param searchUser the user with type and name to search for
     * @return the user if found, null otherwise
     */
    public User getUser(User searchUser) {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.getType().equals(searchUser.getType()) && user.getName().equals(searchUser.getName())) {
                return user;
            }
        }
        return null;
    }
    /**
     * Returns the list of users.
     *
     * @return the list of users
     */
    public List<User> getUserList() {
        return userList;
    }
    /**
     * Fills the personal information table with the current user's details.
     *
     * @param personalInfoTable the table to be filled
     * @param currentUser the current user whose details are to be displayed
     */
    public void fillPersonalInfoTable(Vector<Vector<String>> personalInfoTable, User currentUser) {
        personalInfoTable.clear();
        Vector<String> row = new Vector<>();
        row.add(currentUser.getType());
        row.add(currentUser.getName());
        row.add(currentUser.getGender());
        row.add(dateSdf.format(currentUser.getDob()));
        row.add(currentUser.getContact());
        row.add(currentUser.getAddress());
        personalInfoTable.add(row);
    }
    /**
     * Loads the user list from a file.
     *
     * @param filePath the path of the file to load from
     */
    public void loadUser(String filePath) {
        userUtil.loadUserList(userList, filePath);
    }
    /**
     * Saves the user list to a file.
     *
     * @param filePath the path of the file to save to
     */
    public void saveUser(String filePath) {
        userUtil.saveUserList(userList, filePath);
    }

    /**
     * Loads the parent-kid relationships from a file.
     *
     * @param filePath the path of the file to load from
     */
    public void loadParentKid(String filePath) {
        parentKidUtil.loadParentKidList(this, userList, filePath);
    }
    /**
     * Saves the parent-kid relationships to a file.
     *
     * @param filePath the path of the file to save to
     */
    public void saveParentKid(String filePath) {
        parentKidUtil.saveParentKidList(userList, filePath);
    }
    /**
     * Fills the parent-kid information table with the current parent's kids' details.
     *
     * @param parentKidTable the table to be filled
     * @param currentUser the current parent whose kids' details are to be displayed
     */
    public void fillParentKidTable(Vector<Vector<String>> parentKidTable, User currentUser) {
        parentKidTable.clear();
        List<User> parentKidList = ((Parent) currentUser).getKidList();
        for (int i = 0; i < parentKidList.size(); i++) {
            Vector<String> row = new Vector<>();
            row.add(parentKidList.get(i).getName());
            row.add(parentKidList.get(i).getGender());
            row.add(dateSdf.format(parentKidList.get(i).getDob()));
            row.add(parentKidList.get(i).getContact());
            row.add(parentKidList.get(i).getAddress());
            parentKidTable.add(row);
        }
    }
    /**
     * Returns a list of candidate kids for a parent.
     *
     * @return the list of candidate kids
     */
    public List<User> getCandidateKidList() {
        List<User> candidateKidList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            User currentUser = userList.get(i);
            if (currentUser.getType().equals("Parent")) {
                continue;
            }
            candidateKidList.add(currentUser);
        }
        return candidateKidList;
    }
}
