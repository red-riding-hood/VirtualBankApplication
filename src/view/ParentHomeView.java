package view;

import controller.AccountController;
import controller.GoalController;
import controller.TransactionController;
import controller.UserController;
import model.Goal;
import model.Parent;
import model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

/**
 * ParentHomeView.java
 *
 * This class represents the main view for parents who have successfully logged in.
 * It provides functionalities to manage kids, view transaction history, and manage tasks.
 *
 * @version 1.0
 */
public class ParentHomeView implements ActionListener {
    private Vector<Vector<String>> transactionHistoryTable = new Vector<>();
    private Vector<Vector<String>> parentKidTable = new Vector<>();
    private Vector<Vector<String>> taskTable = new Vector<>();
    private User currentUser;
    private UserController currentUserController;
    private AccountController currentAccountController;
    private TransactionController currentTransactionController;
    private GoalController currentGoalController;

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

    public Vector<String> getKidsColumns() {
        Vector<String> columns = new Vector<>();
        columns.add("Kid name");
        columns.add("Kid Gender");
        columns.add("Kid DOB");
        columns.add("Kid Contact");
        columns.add("Kid Address");
        return columns;
    }

    public Vector<String> getTaskColumns() {
        Vector<String> columns = new Vector<>();
        columns.add("Kid");
        columns.add("Name");
        columns.add("Description");
        columns.add("Start Time");
        columns.add("Deadline");
        columns.add("Status");
        return columns;
    }

    public void addKidHelper() {
        JFrame addKidFrame = new JFrame("Kids Management");
        addKidFrame.setSize(800, 730);
        addKidFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addKidFrame.setLocationRelativeTo(null);
        addKidFrame.setResizable(false);
        addKidFrame.setLayout(new FlowLayout());
        addKidFrame.setVisible(true);

        ImageIcon background = new ImageIcon("src/images/3.jpg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(new FlowLayout());
        addKidFrame.setContentPane(backgroundLabel);

        JLabel addKidLabel = new JLabel("Account: ");
        addKidLabel.setPreferredSize(new Dimension(80, 60));
        addKidFrame.add(addKidLabel);
        JComboBox<String> candidateKidListComboBox = new JComboBox<>();
        candidateKidListComboBox.setPreferredSize(new Dimension(250, 30));
        List<User> candidateKidList = currentUserController.getCandidateKidList();
        for (int i = 0; i < candidateKidList.size(); i++) {
            User candidateKid = candidateKidList.get(i);
            candidateKidListComboBox.addItem(candidateKid.getName());
        }
        addKidFrame.add(candidateKidListComboBox);

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 40));
        addKidFrame.add(submitButton);

        UIManager.put("OptionPane.okButtonText", "Sure");
        submitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                User selectedKid = candidateKidList.get(candidateKidListComboBox.getSelectedIndex());
                Parent parent = (Parent) currentUser;
                if (parent.getKidList().contains(selectedKid)) {
                    JOptionPane.showMessageDialog(null, "This kid is already added to your list!", "Hint", 2);
                } else {
                    parent.addKid(selectedKid);
                    JOptionPane.showMessageDialog(null, "Add kid successfully！", "Hint", 2);
                }
                addKidFrame.dispose();
            }
        });
    }

    public void kidsManagement() {
        JFrame kidFrame = new JFrame("Kids Management");
        kidFrame.setSize(800, 730);
        kidFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        kidFrame.setLocationRelativeTo(null);
        kidFrame.setResizable(false);
        kidFrame.setLayout(new FlowLayout());
        kidFrame.setVisible(true);

        ImageIcon background = new ImageIcon("src/images/3.jpg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(new FlowLayout());
        kidFrame.setContentPane(backgroundLabel);

        Vector<String> columns = getKidsColumns();
        currentUserController.fillParentKidTable(parentKidTable, currentUser);
        JTable jTable = new JTable(parentKidTable, columns);
        jTable.setSize(750, 600);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(700, 500));
        kidFrame.add(jScrollPane);

        JButton addKidButton = new JButton("Add Kid");
        addKidButton.setPreferredSize(new Dimension(100, 40));
        kidFrame.add(addKidButton);

        addKidButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                addKidHelper();
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
        currentTransactionController.fillKidTransactionHistoryTable(transactionHistoryTable,currentUser);
        JTable jTable = new JTable(transactionHistoryTable, columns);
        jTable.setSize(750, 600);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(600, 500));
        transactionHistoryFrame.add(jScrollPane);
    }

    public void task() {
        JFrame taskFrame = new JFrame("Task");
        taskFrame.setSize(800, 730);
        taskFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        taskFrame.setLocationRelativeTo(null);
        taskFrame.setResizable(false);
        taskFrame.setLayout(new FlowLayout());
        taskFrame.setVisible(true);

        ImageIcon background = new ImageIcon("src/images/3.jpg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setLayout(new FlowLayout());
        taskFrame.setContentPane(backgroundLabel);

        Vector<String> columns = getTaskColumns();
        List<Goal> kidTaskList = currentGoalController.fillKidTaskTable(taskTable, currentUser);
        JTable jTable = new JTable(taskTable, columns);
        jTable.setSize(750, 600);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(700, 500));
        taskFrame.add(jScrollPane);

        JButton finishTaskButton = new JButton("Finish");
        finishTaskButton.setPreferredSize(new Dimension(100, 40));
        taskFrame.add(finishTaskButton);

        JButton overdueTaskButton = new JButton("Overdue");
        overdueTaskButton.setPreferredSize(new Dimension(100, 40));
        taskFrame.add(overdueTaskButton);

        finishTaskButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Goal selectedTask = kidTaskList.get(jTable.getSelectedRow());
                selectedTask.setStatus("Finished");
                currentGoalController.updateGoal(selectedTask);
                currentGoalController.fillKidTaskTable(taskTable, currentUser);
            }
        });

        overdueTaskButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Goal selectedTask = kidTaskList.get(jTable.getSelectedRow());
                selectedTask.setStatus("Overdue");
                currentGoalController.updateGoal(selectedTask);
                currentGoalController.fillKidTaskTable(taskTable, currentUser);
            }
        });
    }

    public void home(UserController userController, AccountController accountController, TransactionController transactionController, GoalController goalController, User loginUser) {
        currentUserController = userController;
        currentAccountController = accountController;
        currentTransactionController = transactionController;
        currentGoalController = goalController;
        currentUser = loginUser;


        JFrame homeFrame = new JFrame("Virtual Bank Application - Parent Home");
        homeFrame.setSize(700, 330);
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

        JMenu kidsManagementMenu = new JMenu("Kids Management");
        JMenu transactionMenu = new JMenu("Transaction");
        JMenu taskMenu = new JMenu("Task");

        JMenuItem kidsManagementItem = new JMenuItem("Kids Management");
        JMenuItem transactionItem = new JMenuItem("Transaction History");
        JMenuItem taskItem = new JMenuItem("Task");

        kidsManagementItem.addActionListener(this);
        transactionItem.addActionListener(this);
        taskItem.addActionListener(this);

        kidsManagementMenu.add(kidsManagementItem);
        transactionMenu.add(transactionItem);
        taskMenu.add(taskItem);

        menuBar.add(kidsManagementMenu);
        menuBar.add(transactionMenu);
        menuBar.add(taskMenu);

        homeFrame.add(menuBar);

        Vector<String> columns = getKidsColumns();
        currentUserController.fillParentKidTable(parentKidTable, currentUser);
        JTable jTable = new JTable(parentKidTable, columns);
        jTable.setSize(750, 600);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(600, 100));
        homeFrame.add(jScrollPane);

//        homeFrame.add(jScrollPane);
        homeFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Parent home window closing!");
                currentUserController.saveUser("src/data/user.txt");
                currentAccountController.saveAccount("src/data/account.txt");
                currentTransactionController.saveTransaction("src/data/transaction.txt");
                currentGoalController.saveGoal("src/data/goal.txt");
                currentUserController.saveParentKid("src/data/parentkid.txt");
                super.windowClosing(e);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Kids Management")) {
            kidsManagement();
        } else if (command.equals("Transaction History")) {
            transaction();
        } else if (command.equals("Task")) {
            task();
        }
    }
}
