package view;

import controller.UserController;
import model.Kid;
import model.Parent;
import model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * UserRegisterView.java
 *
 * This class represents the view for the registration screen of the application.
 * It handles user registration for both kids and parents, validating inputs and
 * creating new user objects accordingly.
 *
 * @version 1.0
 */
public class UserRegisterView {
    private SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Displays the registration screen and handles user registration.
     *
     * @param userController the controller for user-related operations
     */
    public void register(UserController userController) {
        JFrame registerFrame = new JFrame("Virtual Bank Application - Register");
        registerFrame.setSize(400, 700);
        registerFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setResizable(false);
        registerFrame.setLayout(new FlowLayout());
        registerFrame.setVisible(true);

        JLabel userTypeLabel = new JLabel("Usertype: ");
        userTypeLabel.setPreferredSize(new Dimension(150, 60));
        registerFrame.add(userTypeLabel);
        JComboBox<String> userTypeComboBox = new JComboBox<>();
        userTypeComboBox.setPreferredSize(new Dimension(180, 30));
        userTypeComboBox.addItem("Kid");
        userTypeComboBox.addItem("Parent");
        registerFrame.add(userTypeComboBox);

        JLabel userNameLabel = new JLabel("Username: ");
        userNameLabel.setPreferredSize(new Dimension(150, 60));
        registerFrame.add(userNameLabel);
        JTextField userName = new JTextField();
        userName.setPreferredSize(new Dimension(180, 30));
        registerFrame.add(userName);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setPreferredSize(new Dimension(150, 60));
        registerFrame.add(passwordLabel);
        JPasswordField password = new JPasswordField();
        password.setPreferredSize(new Dimension(180, 30));
        registerFrame.add(password);

        JLabel genderLabel = new JLabel("Gender: ");
        genderLabel.setPreferredSize(new Dimension(150, 60));
        registerFrame.add(genderLabel);
        JComboBox<String> genderComboBox = new JComboBox<>();
        genderComboBox.setPreferredSize(new Dimension(180, 30));
        genderComboBox.addItem("Male");
        genderComboBox.addItem("Female");
        registerFrame.add(genderComboBox);

        JLabel dobLabel = new JLabel("DOB(yyyy-MM-dd): ");
        dobLabel.setPreferredSize(new Dimension(150, 60));
        registerFrame.add(dobLabel);
        JTextField dob = new JTextField();
        dob.setPreferredSize(new Dimension(180, 30));
        registerFrame.add(dob);

        JLabel contactLabel = new JLabel("Contact: ");
        contactLabel.setPreferredSize(new Dimension(150, 60));
        registerFrame.add(contactLabel);
        JTextField contact = new JTextField();
        contact.setPreferredSize(new Dimension(180, 30));
        registerFrame.add(contact);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setPreferredSize(new Dimension(150, 60));
        registerFrame.add(addressLabel);
        JTextField address = new JTextField();
        address.setPreferredSize(new Dimension(180, 30));
        registerFrame.add(address);

        JLabel parentRoleLabel = new JLabel("Parent Role: ");
        parentRoleLabel.setPreferredSize(new Dimension(150, 60));
        registerFrame.add(parentRoleLabel);
        JComboBox<String> parentRoleComboBox = new JComboBox<>();
        parentRoleComboBox.setPreferredSize(new Dimension(180, 30));
        parentRoleComboBox.addItem("Father");
        parentRoleComboBox.addItem("Mother");
        parentRoleComboBox.setEnabled(false);
        registerFrame.add(parentRoleComboBox);

        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(100, 40));
        registerFrame.add(registerButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(100, 40));
        registerFrame.add(clearButton);

        UIManager.put("OptionPane.okButtonText", "Sure");
        userTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userTypeComboBox.getSelectedIndex() == 0) {
                    parentRoleComboBox.setEnabled(false);
                } else {
                    parentRoleComboBox.setEnabled(true);
                }
            }
        });
        registerButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                User newUser = null;
                try {
                    String userType = "Kid";
                    if (userTypeComboBox.getSelectedIndex() == 1) {
                        userType = "Parent";
                    }
                    String gender = "Male";
                    if (genderComboBox.getSelectedIndex() == 1) {
                        gender = "Female";
                    }
                    String parentRole = "Father";
                    if (parentRoleComboBox.getSelectedIndex() == 1) {
                        gender = "Mother";
                    }

                    if (userType.equals("Kid")) {
                        newUser = new Kid(userType, userName.getText(), password.getText(), gender, dateSdf.parse(dob.getText()), contact.getText(), address.getText());
                    } else {
                        newUser = new Parent(userType, userName.getText(), password.getText(), gender, dateSdf.parse(dob.getText()), contact.getText(), address.getText(), parentRole);
                    }
                    if (userController.getUser(newUser) != null) {
                        JOptionPane.showMessageDialog(null, "User has existed, register failed! ","Oops...", 2);
                        return;
                    }
                    userController.addUser(newUser);
                    JOptionPane.showMessageDialog(null, "Register successfully! ","Hint", 2);
                    registerFrame.dispose();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid format of DOB, register failed! ","Oops...", 2);
                }
            }
        });

        clearButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                userName.setText("");
                password.setText("");
                dob.setText("");
                contact.setText("");
                address.setText("");
            }
        });
    }
}
