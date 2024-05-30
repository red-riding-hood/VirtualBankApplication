package view;

import controller.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * View - for kids that login successfully
 */
public class KidHomeView implements ActionListener {
    private SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat datetimeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Vector<Vector<String>> personalInfoTable = new Vector<>();
    private Vector<Vector<String>> accountInfoTable = new Vector<>();
    private Vector<Vector<String>> transactionHistoryTable = new Vector<>();
    private Vector<Vector<String>> goalTable = new Vector<>();
    private User currentUser;
    private UserController currentUserController;
    private AccountController currentAccountController;
    private TransactionController currentTransactionController;
    private GoalController currentGoalController;
    private DepositController depositController = new DepositController();
    private WithdrawController withdrawController = new WithdrawController();
    private TransferController transferController = new TransferController();

    /**
     * Gets the columns for the personal information table.
     * @return A Vector containing the column names for the personal information table.
     */
    public Vector<String> getPersonalInfoColumns() {
        Vector<String> columns = new Vector<>();
        columns.add("Usertype");
        columns.add("Username");
        columns.add("Gender");
        columns.add("DOB");
        columns.add("Contact");
        columns.add("Address");
        return columns;
    }
    /**
     * Gets the columns for the transaction history table.
     * @return A Vector containing the column names for the transaction history table.
     */

    public Vector<String> getPersonalAccountColumns() {
        Vector<String> columns = new Vector<>();
        columns.add("Accounttype");
        columns.add("Username");
        columns.add("Accountname");
        columns.add("Createtime");
        columns.add("Balance");
        return columns;
    }


    public Vector<String> getTransactionHistoryColumns() {
        Vector<String> columns = new Vector<>();
        columns.add("Source");
        columns.add("Destination");
        columns.add("Type");
        columns.add("Transaction Amount");
        columns.add("Transaction Time");
        columns.add("Remark");
        return columns;
    }
    /**
     * Gets the columns for the goal table.
     * @return A Vector containing the column names for the goal table.
     */
    public Vector<String> getGoalColumns() {
        Vector<String> columns = new Vector<>();
        columns.add("Kid");
        columns.add("Name");
        columns.add("Description");
        columns.add("Start Time");
        columns.add("Deadline");
        columns.add("Status");
        return columns;
    }
    /**
     * Displays and maintains the personal information of the user.
     */
    public void personalInfoMaintain() {
        JFrame maintainFrame = new JFrame("Personal Info Maintain");
        maintainFrame.setSize(410, 530);
        maintainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        maintainFrame.setLocationRelativeTo(null);
        maintainFrame.setResizable(false);
        maintainFrame.setLayout(new FlowLayout());
        maintainFrame.setVisible(true);

        ImageIcon background = new ImageIcon("src/images/4.jpg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(new FlowLayout());
        maintainFrame.setContentPane(backgroundLabel);

        JLabel nameLabel = new JLabel("Username: ");
        nameLabel.setPreferredSize(new Dimension(80, 60));
        maintainFrame.add(nameLabel);
        JTextField name = new JTextField();
        name.setText(currentUser.getName());
        name.setPreferredSize(new Dimension(250, 30));
        maintainFrame.add(name);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setPreferredSize(new Dimension(80, 60));
        maintainFrame.add(passwordLabel);
        JPasswordField password = new JPasswordField();
        password.setText(currentUser.getName());
        password.setPreferredSize(new Dimension(250, 30));
        maintainFrame.add(password);

        JLabel genderLabel = new JLabel("Gender: ");
        genderLabel.setPreferredSize(new Dimension(80, 60));
        maintainFrame.add(genderLabel);
        // 使用JComboBox替代JTextField
        JComboBox<String> gender = new JComboBox<>(new String[]{"Male", "Female"});
        gender.setSelectedItem(currentUser.getGender());
        gender.setPreferredSize(new Dimension(250, 30));
        maintainFrame.add(gender);

        JLabel dobLabel = new JLabel("DOB: ");
        dobLabel.setPreferredSize(new Dimension(80, 60));
        maintainFrame.add(dobLabel);
        JTextField dob = new JTextField();
        dob.setText(dateSdf.format(currentUser.getDob()));
        dob.setPreferredSize(new Dimension(250, 30));
        maintainFrame.add(dob);

        JLabel contactLabel = new JLabel("Contact: ");
        contactLabel.setPreferredSize(new Dimension(80, 60));
        maintainFrame.add(contactLabel);
        JTextField contact = new JTextField();
        contact.setText(currentUser.getContact());
        contact.setPreferredSize(new Dimension(250, 30));
        maintainFrame.add(contact);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setPreferredSize(new Dimension(80, 60));
        maintainFrame.add(addressLabel);
        JTextField address = new JTextField();
        address.setText(currentUser.getAddress());
        address.setPreferredSize(new Dimension(250, 30));
        maintainFrame.add(address);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 40));
        maintainFrame.add(submitButton);

        UIManager.put("OptionPane.okButtonText", "Sure");
        submitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    currentUser.setName(name.getText());
                    currentUser.setGender((String) gender.getSelectedItem());
                    currentUser.setDob(dateSdf.parse(dob.getText()));
                    currentUser.setContact(contact.getText());
                    currentUser.setAddress(address.getText());
                    currentUserController.updateUser(currentUser);
                    JOptionPane.showMessageDialog(null, "Personal info maintain successfully！", "Hint", 2);
                    currentUserController.fillPersonalInfoTable(personalInfoTable, currentUser);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Personal info maintain failed！", "Oops...", 2);
                } finally {
                    maintainFrame.dispose();
                }
            }
        });
    }

    public void accountCreation() {
        JFrame accountCreationFrame = new JFrame("Account Creation");
        accountCreationFrame.setSize(400, 300);
        accountCreationFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        accountCreationFrame.setLocationRelativeTo(null);
        accountCreationFrame.setResizable(false);
        accountCreationFrame.setLayout(new FlowLayout());
        accountCreationFrame.setVisible(true);

        JLabel accountTypeLabel = new JLabel("Account Type: ");
        accountTypeLabel.setPreferredSize(new Dimension(120, 60));
        accountCreationFrame.add(accountTypeLabel);
        JComboBox<String> accountTypeComboBox = new JComboBox<>();
        accountTypeComboBox.setPreferredSize(new Dimension(210, 30));
        accountTypeComboBox.addItem("Current");
        accountTypeComboBox.addItem("Saving");
        accountCreationFrame.add(accountTypeComboBox);

        JLabel accountNameLabel = new JLabel("Account Name: ");
        accountNameLabel.setPreferredSize(new Dimension(120, 60));
        accountCreationFrame.add(accountNameLabel);
        JTextField accountName = new JTextField();
        accountName.setPreferredSize(new Dimension(210, 30));
        accountCreationFrame.add(accountName);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 40));
        accountCreationFrame.add(submitButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(100, 40));
        accountCreationFrame.add(clearButton);

        UIManager.put("OptionPane.okButtonText", "Sure");
        submitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String selectedAccountType = (String) accountTypeComboBox.getSelectedItem();
                Account newAccount = new Account(selectedAccountType, currentUser, accountName.getText(), new Date(), 0.0);
                if (currentAccountController.getAccount(newAccount) != null) {
                    JOptionPane.showMessageDialog(null, "Account has existed, create account failed! ","Oops...", 2);
                    return;
                }
                currentAccountController.addAccount(newAccount);
                JOptionPane.showMessageDialog(null, "Create account successfully！", "Hint", 2);
                accountCreationFrame.dispose();
            }
        });
        clearButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                accountName.setText("");
            }
        });
    }

    public void accountDeposit() {
        JFrame accountDepositFrame = new JFrame("Account Deposit");
        accountDepositFrame.setSize(400, 300);
        accountDepositFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        accountDepositFrame.setLocationRelativeTo(null);
        accountDepositFrame.setResizable(false);
        accountDepositFrame.setLayout(new FlowLayout());
        accountDepositFrame.setVisible(true);

        JLabel accountLabel = new JLabel("Account: ");
        accountLabel.setPreferredSize(new Dimension(80, 60));
        accountDepositFrame.add(accountLabel);
        JComboBox<String> userAccountListComboBox = new JComboBox<>();
        userAccountListComboBox.setPreferredSize(new Dimension(250, 30));
        List<Account> userAccountList = currentAccountController.getAccountListByUser(currentUser);
        for (int i = 0; i < userAccountList.size(); i++) {
            Account userAccount = userAccountList.get(i);
            userAccountListComboBox.addItem(userAccount.getAccountName() + " (Balance: " + String.format("%.2f", userAccount.getBalance()) + ")");
        }
        accountDepositFrame.add(userAccountListComboBox);

        JLabel depositAmountLabel = new JLabel("Deposit Amount: ");
        depositAmountLabel.setPreferredSize(new Dimension(120, 60));
        accountDepositFrame.add(depositAmountLabel);
        JTextField depositAmount = new JTextField();
        depositAmount.setPreferredSize(new Dimension(210, 30));
        accountDepositFrame.add(depositAmount);

        JLabel remarkLabel = new JLabel("Remark: ");
        remarkLabel.setPreferredSize(new Dimension(80, 60));
        accountDepositFrame.add(remarkLabel);
        JTextField remark = new JTextField();
        remark.setPreferredSize(new Dimension(250, 30));
        accountDepositFrame.add(remark);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 40));
        accountDepositFrame.add(submitButton);

        UIManager.put("OptionPane.okButtonText", "Sure");
        submitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Account selectedAccount = userAccountList.get(userAccountListComboBox.getSelectedIndex());
                if (!depositController.deposit(currentTransactionController, selectedAccount, Double.parseDouble(depositAmount.getText()), remark.getText())) {
                    JOptionPane.showMessageDialog(null, "Not enough balance, deposit failed！", "Oops...", 2);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Deposit successfully！", "Hint", 2);
                accountDepositFrame.dispose();
            }
        });
    }

    public void accountWithdraw() {
        JFrame accountWithdrawFrame = new JFrame("Account Withdraw");
        accountWithdrawFrame.setSize(400, 300);
        accountWithdrawFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        accountWithdrawFrame.setLocationRelativeTo(null);
        accountWithdrawFrame.setResizable(false);
        accountWithdrawFrame.setLayout(new FlowLayout());
        accountWithdrawFrame.setVisible(true);

        JLabel accountLabel = new JLabel("Account: ");
        accountLabel.setPreferredSize(new Dimension(80, 60));
        accountWithdrawFrame.add(accountLabel);
        JComboBox<String> userAccountListComboBox = new JComboBox<>();
        userAccountListComboBox.setPreferredSize(new Dimension(250, 30));
        List<Account> userAccountList = currentAccountController.getAccountListByUser(currentUser);
        for (int i = 0; i < userAccountList.size(); i++) {
            Account userAccount = userAccountList.get(i);
            userAccountListComboBox.addItem(userAccount.getAccountName() + " (Balance: " + String.format("%.2f", userAccount.getBalance()) + ")");
        }
        accountWithdrawFrame.add(userAccountListComboBox);

        JLabel withdrawAmountLabel = new JLabel("Withdraw Amount: ");
        withdrawAmountLabel.setPreferredSize(new Dimension(120, 60));
        accountWithdrawFrame.add(withdrawAmountLabel);
        JTextField withdrawAmount = new JTextField();
        withdrawAmount.setPreferredSize(new Dimension(210, 30));
        accountWithdrawFrame.add(withdrawAmount);

        JLabel remarkLabel = new JLabel("Remark: ");
        remarkLabel.setPreferredSize(new Dimension(80, 60));
        accountWithdrawFrame.add(remarkLabel);
        JTextField remark = new JTextField();
        remark.setPreferredSize(new Dimension(250, 30));
        accountWithdrawFrame.add(remark);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 40));
        accountWithdrawFrame.add(submitButton);

        UIManager.put("OptionPane.okButtonText", "Sure");
        submitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Account selectedAccount = userAccountList.get(userAccountListComboBox.getSelectedIndex());
                if (!withdrawController.withdraw(currentTransactionController, selectedAccount, Double.parseDouble(withdrawAmount.getText()), remark.getText())) {
                    JOptionPane.showMessageDialog(null, "Invalid amount, withdraw failed！", "Oops...", 2);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Withdraw successfully！", "Hint", 2);
                accountWithdrawFrame.dispose();
            }
        });
    }

    public void accountTransfer() {
        JFrame accountTransferFrame = new JFrame("Account Transfer");
        accountTransferFrame.setSize(400, 400);
        accountTransferFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        accountTransferFrame.setLocationRelativeTo(null);
        accountTransferFrame.setResizable(false);
        accountTransferFrame.setLayout(new FlowLayout());
        accountTransferFrame.setVisible(true);

        JLabel yourAccountLabel = new JLabel("Your Account: ");
        yourAccountLabel.setPreferredSize(new Dimension(80, 60));
        accountTransferFrame.add(yourAccountLabel);
        JComboBox<String> userAccountListComboBox = new JComboBox<>();
        userAccountListComboBox.setPreferredSize(new Dimension(250, 30));
        List<Account> userAccountList = currentAccountController.getAccountListByUser(currentUser);
        for (int i = 0; i < userAccountList.size(); i++) {
            Account userAccount = userAccountList.get(i);
            userAccountListComboBox.addItem(userAccount.getAccountName() + " (Balance: " + String.format("%.2f", userAccount.getBalance()) + ")");
        }
        accountTransferFrame.add(userAccountListComboBox);

        JLabel transferAccountLabel = new JLabel("Transfer Account: ");
        transferAccountLabel.setPreferredSize(new Dimension(80, 60));
        accountTransferFrame.add(transferAccountLabel);
        JComboBox<String> transferAccountListComboBox = new JComboBox<>();
        transferAccountListComboBox.setPreferredSize(new Dimension(250, 30));
        List<Account> transferAccountList = currentAccountController.getAccountList();
        for (int i = 0; i < transferAccountList.size(); i++) {
            Account transferAccount = transferAccountList.get(i);
            transferAccountListComboBox.addItem(transferAccount.getAccountName() + " (Balance: " + String.format("%.2f", transferAccount.getBalance()) + ")");
        }
        accountTransferFrame.add(transferAccountListComboBox);

        JLabel transferAmountLabel = new JLabel("Transfer Amount: ");
        transferAmountLabel.setPreferredSize(new Dimension(120, 60));
        accountTransferFrame.add(transferAmountLabel);
        JTextField transferAmount = new JTextField();
        transferAmount.setPreferredSize(new Dimension(210, 30));
        accountTransferFrame.add(transferAmount);

        JLabel remarkLabel = new JLabel("Remark: ");
        remarkLabel.setPreferredSize(new Dimension(80, 60));
        accountTransferFrame.add(remarkLabel);
        JTextField remark = new JTextField();
        remark.setPreferredSize(new Dimension(250, 30));
        accountTransferFrame.add(remark);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 40));
        accountTransferFrame.add(submitButton);

        UIManager.put("OptionPane.okButtonText", "Sure");
        submitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Account selectedYourAccount = userAccountList.get(userAccountListComboBox.getSelectedIndex());
                Account selectedTransferAccount = transferAccountList.get(transferAccountListComboBox.getSelectedIndex());
                if (!transferController.transfer(currentTransactionController, selectedYourAccount, selectedTransferAccount, Double.parseDouble(transferAmount.getText()), remark.getText())) {
//                    JOptionPane.showMessageDialog(null, "Not enough money, transfer failed！", "Oops...", 2);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Transfer successfully！", "Hint", 2);
                accountTransferFrame.dispose();
            }
        });
    }

    public void transaction() {
        JFrame transactionHistoryFrame = new JFrame("Transaction History");
        transactionHistoryFrame.setSize(800, 730);
        transactionHistoryFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        transactionHistoryFrame.setLocationRelativeTo(null);
        transactionHistoryFrame.setResizable(false);
        transactionHistoryFrame.setLayout(new FlowLayout());
        transactionHistoryFrame.setVisible(true);

        ImageIcon background = new ImageIcon("src/images/3.jpg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(new FlowLayout());
        transactionHistoryFrame.setContentPane(backgroundLabel);

        Vector<String> columns = getTransactionHistoryColumns();
        currentTransactionController.fillTransactionHistoryTable(transactionHistoryTable,currentUser);        JTable jTable = new JTable(transactionHistoryTable, columns);
        jTable.setSize(750, 600);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(700, 500));
        transactionHistoryFrame.add(jScrollPane);
    }

    public void addGoalHelper() {
        JFrame addGoalFrame = new JFrame("Add Goal");
        addGoalFrame.setSize(400, 700);
        addGoalFrame.setDefaultCloseOperation(3);
        addGoalFrame.setLocationRelativeTo(null);
        addGoalFrame.setResizable(false);
        addGoalFrame.setLayout(new FlowLayout());
        addGoalFrame.setVisible(true);

        JLabel goalNameLabel = new JLabel("Goalname: ");
        goalNameLabel.setPreferredSize(new Dimension(150, 60));
        addGoalFrame.add(goalNameLabel);
        JTextField goalName = new JTextField();
        goalName.setPreferredSize(new Dimension(180, 30));
        addGoalFrame.add(goalName);

        JLabel descriptionLabel = new JLabel("Description: ");
        descriptionLabel.setPreferredSize(new Dimension(150, 60));
        addGoalFrame.add(descriptionLabel);
        JTextField description = new JTextField();
        description.setPreferredSize(new Dimension(180, 30));
        addGoalFrame.add(description);

        JLabel startTimeLabel = new JLabel("<html>Start Time<br>(yyyy-MM-dd HH:mm:ss): </html>");
        startTimeLabel.setPreferredSize(new Dimension(150, 60));
        addGoalFrame.add(startTimeLabel);
        JTextField startTime = new JTextField();
        startTime.setPreferredSize(new Dimension(180, 30));
        addGoalFrame.add(startTime);

        JLabel deadlineLabel = new JLabel("<html>Deadline<br>(yyyy-MM-dd HH:mm:ss): </html> ");
        deadlineLabel.setPreferredSize(new Dimension(150, 60));
        addGoalFrame.add(deadlineLabel);
        JTextField deadline = new JTextField();
        deadline.setPreferredSize(new Dimension(180, 30));
        addGoalFrame.add(deadline);

        JLabel statusLabel = new JLabel("Status: ");
        statusLabel.setPreferredSize(new Dimension(150, 60));
        addGoalFrame.add(statusLabel);
        JComboBox<String> statusComboBox = new JComboBox<>();
        statusComboBox.setPreferredSize(new Dimension(180, 30));
        statusComboBox.addItem("Processing");
        statusComboBox.addItem("Finished");
        statusComboBox.addItem("Overdue");
        addGoalFrame.add(statusComboBox);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 40));
        addGoalFrame.add(submitButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(100, 40));
        addGoalFrame.add(clearButton);

        UIManager.put("OptionPane.okButtonText", "Sure");
        submitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Goal newGoal = null;
                try {
                    String status = "Processing";
                    if (statusComboBox.getSelectedIndex() == 1) {
                        status = "Finished";
                    } else if (statusComboBox.getSelectedIndex() == 2) {
                        status = "Overdue";
                    }
                    newGoal = new Goal(currentUser, goalName.getText(), description.getText(), datetimeSdf.parse(startTime.getText()), datetimeSdf.parse(deadline.getText()), status);
                    if (currentGoalController.getGoal(newGoal) != null) {
                        JOptionPane.showMessageDialog(null, "Goal has existed, add failed! ","Oops...", 2);
                        return;
                    }
                    currentGoalController.addGoal(newGoal);
                    currentGoalController.fillGoalTable(goalTable, currentUser);
                    JOptionPane.showMessageDialog(null, "Add goal successfully! ","Hint", 2);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid format of DOB, add Goal failed! ","Oops...", 2);
                } finally {
                    addGoalFrame.dispose();
                }
            }
        });

        clearButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                goalName.setText("");
                description.setText("");
                startTime.setText("");
                deadline.setText("");
            }
        });
    }

    public void goal() {
        JFrame goalFrame = new JFrame("Goal");
        goalFrame.setSize(800, 730);
        goalFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        goalFrame.setLocationRelativeTo(null);
        goalFrame.setResizable(false);
        goalFrame.setLayout(new FlowLayout());
        goalFrame.setVisible(true);

        ImageIcon background = new ImageIcon("src/images/3.jpg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(new FlowLayout());
        goalFrame.setContentPane(backgroundLabel);

        Vector<String> columns = getGoalColumns();
        currentGoalController.fillGoalTable(goalTable, currentUser);
        JTable jTable = new JTable(goalTable, columns);
        jTable.setSize(750, 600);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(700, 500));
        goalFrame.add(jScrollPane);

        JButton addGoalButton = new JButton("Add Goal");
        addGoalButton.setPreferredSize(new Dimension(100, 40));
        goalFrame.add(addGoalButton);

        addGoalButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                addGoalHelper();
            }
        });
    }

    public void home(UserController userController, AccountController accountController, TransactionController transactionController, GoalController goalController, User loginUser) {
        currentUserController = userController;
        currentAccountController = accountController;
        currentTransactionController = transactionController;
        currentGoalController = goalController;
        currentUser = loginUser;

        JFrame homeFrame = new JFrame("Virtual Bank Application - Kid Home");
        homeFrame.setSize(710, 330);
        homeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setResizable(false);
        homeFrame.setLayout(new FlowLayout());
        homeFrame.setVisible(true);

        ImageIcon background = new ImageIcon("src/images/2.jpg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(new FlowLayout());
        homeFrame.setContentPane(backgroundLabel);

        JMenuBar menuBar = new JMenuBar();

        JMenu personalMenu = new JMenu("Personal");
        JMenu accountMenu = new JMenu("Account");
        JMenu transactionMenu = new JMenu("Transaction");
        JMenu goalMenu = new JMenu("Goal");

        JMenuItem personalMaintainItem = new JMenuItem("Personal Info Maintain");
        JMenuItem accountCreationItem = new JMenuItem("Creation");
        JMenuItem accountDepositItem = new JMenuItem("Deposit");
        JMenuItem accountWithdrawItem = new JMenuItem("Withdraw");
        JMenuItem accountTransferItem = new JMenuItem("Transfer");
        JMenuItem transactionItem = new JMenuItem("Transaction History");
        JMenuItem goalItem = new JMenuItem("Goal");

        Vector<String> columns = getPersonalInfoColumns();
        currentUserController.fillPersonalInfoTable(personalInfoTable, currentUser);
        JTable jTable = new JTable(personalInfoTable, columns);
        jTable.setSize(600, 50);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(600, 50));


        Vector<String> columns1 = getPersonalAccountColumns();
        currentAccountController.fillAccountInfoTable(accountInfoTable, currentUser);
        JTable jTable1 = new JTable(accountInfoTable, columns1);
        jTable1.setSize(600, 50);
        JScrollPane jScrollPane1 = new JScrollPane(jTable1);
        jScrollPane1.setPreferredSize(new Dimension(600, 150));

        personalMaintainItem.addActionListener(this);
        accountCreationItem.addActionListener(this);
        accountDepositItem.addActionListener(this);
        accountWithdrawItem.addActionListener(this);
        accountTransferItem.addActionListener(this);
        transactionItem.addActionListener(this);
        goalItem.addActionListener(this);

        personalMenu.add(personalMaintainItem);
        accountMenu.add(accountCreationItem);
        accountMenu.add(accountDepositItem);
        accountMenu.add(accountWithdrawItem);
        accountMenu.add(accountTransferItem);
        transactionMenu.add(transactionItem);
        goalMenu.add(goalItem);

        menuBar.add(personalMenu);
        menuBar.add(accountMenu);
        menuBar.add(transactionMenu);
        menuBar.add(goalMenu);

        homeFrame.add(menuBar);
        homeFrame.add(jScrollPane);
        homeFrame.add(jScrollPane1);
        homeFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Kid home window closing!");
                currentUserController.saveUser("src/data/user.txt");
                currentAccountController.saveAccount("src/data/account.txt");
                currentTransactionController.saveTransaction("src/data/transaction.txt");
                currentGoalController.saveGoal("src/data/goal.txt");
                super.windowClosing(e);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Personal Info Maintain")) {
            personalInfoMaintain();
        } else if (command.equals("Creation")) {
            accountCreation();
        } else if (command.equals("Deposit")) {
            accountDeposit();
        } else if (command.equals("Withdraw")) {
            accountWithdraw();
        } else if (command.equals("Transfer")) {
            accountTransfer();
        } else if (command.equals("Transaction History")) {
            transaction();
        } else if (command.equals("Goal")) {
            goal();
        }
    }
}
