package view;

import controller.AccountController;
import controller.GoalController;
import controller.TransactionController;
import controller.UserController;
import model.Account;
import model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * UserLoginView.java
 *
 * This class represents the view for the login screen of the application.
 * It handles user authentication and provides options to login, register, or clear input fields.
 * Depending on the user type, it navigates to the appropriate home view.
 *
 * @version 1.0
 */
public class UserLoginView {
    /**
     * Displays the login screen and handles user authentication.
     *
     * @param userController the controller for user-related operations
     * @param accountController the controller for account-related operations
     * @param transactionController the controller for transaction-related operations
     * @param goalController the controller for goal-related operations
     */
    public void login(UserController userController, AccountController accountController, TransactionController transactionController, GoalController goalController) {
        JFrame loginFrame = new JFrame("Virtual Bank Application - Login");
        loginFrame.setSize(410, 380);
        loginFrame.setDefaultCloseOperation(3);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setResizable(false);
        loginFrame.setLayout(new FlowLayout());

        // 加载背景图片
        ImageIcon background = new ImageIcon("src/images/1.jpg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(new FlowLayout());
        loginFrame.setContentPane(backgroundLabel);

        JLabel titleLabel = new JLabel("Virtual Bank Application");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 24)); // 设置字体和大小
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // 居中对齐
        titleLabel.setForeground(Color.DARK_GRAY); // 设置字体颜色为灰色
        titleLabel.setPreferredSize(new Dimension(400, 50)); // 设置首选尺寸
        loginFrame.add(titleLabel);

        JLabel userTypeLabel = new JLabel("Usertype: ");
        userTypeLabel.setPreferredSize(new Dimension(150, 60));
        loginFrame.add(userTypeLabel);
        JComboBox<String> userTypeComboBox = new JComboBox<>();
        userTypeComboBox.setPreferredSize(new Dimension(180, 30));
        userTypeComboBox.addItem("Kid");
        userTypeComboBox.addItem("Parent");
        loginFrame.add(userTypeComboBox);

        JLabel userNameLabel = new JLabel("Username: ");
        userNameLabel.setPreferredSize(new Dimension(80, 60));
        loginFrame.add(userNameLabel);
        JTextField userName = new JTextField();
        userName.setPreferredSize(new Dimension(250, 30));
        loginFrame.add(userName);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setPreferredSize(new Dimension(80, 60));
        loginFrame.add(passwordLabel);
        JPasswordField password = new JPasswordField();
        password.setPreferredSize(new Dimension(250, 30));
        loginFrame.add(password);

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 40));
        loginFrame.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(100, 40));
        loginFrame.add(registerButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(100, 40));
        loginFrame.add(clearButton);
        loginFrame.setVisible(true);

        UIManager.put("OptionPane.okButtonText", "Sure");
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String userType = "Kid";
                if (userTypeComboBox.getSelectedIndex() == 1) {
                    userType = "Parent";
                }

                User searchUser = userController.login(userName.getText(), userType, password.getText());
                if (searchUser == null) {
                    JOptionPane.showMessageDialog(null, "User does not exist! ","Oops...", 2);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Login successfully! ","Hint", 2);
                if (userType.equals("Kid")) {
                    KidHomeView kidHomeView = new KidHomeView();
                    kidHomeView.home(userController, accountController, transactionController, goalController, searchUser);
                } else {
                    ParentHomeView parentHomeView = new ParentHomeView();
                    parentHomeView.home(userController, accountController, transactionController, goalController, searchUser);
                }
            }
        });

        registerButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                UserRegisterView userRegisterView = new UserRegisterView();
                userRegisterView.register(userController);
            }
        });

        clearButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                userName.setText("");
                password.setText("");
            }
        });

        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Parent home window closing!");
                userController.saveUser("src/data/user.txt");
                accountController.saveAccount("src/data/account.txt");
                transactionController.saveTransaction("src/data/transaction.txt");
                goalController.saveGoal("src/data/goal.txt");
                userController.saveParentKid("src/data/parentkid.txt");
                super.windowClosing(e);
            }
        });
    }
}
