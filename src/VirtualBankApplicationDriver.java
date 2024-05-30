import controller.AccountController;
import controller.GoalController;
import controller.TransactionController;
import controller.UserController;
import view.UserLoginView;

/**
 * Title        : VirtualBankApplicationDriver.java
 * Description  : This class serves as the entry point for the Virtual Bank Application.
 *                It initializes the controllers and starts the user login view.
 *                When starting the application, a new instance of this class is created.
 *                It represents the main driver for the application initialization and execution.
 * @version     : 1.0
 */
public class VirtualBankApplicationDriver {
    private static UserController userController = new UserController();
    private static AccountController accountController = new AccountController();
    private static TransactionController transactionController = new TransactionController();
    private static GoalController goalController = new GoalController();

    /**
     * Initializes the controllers and loads data from specified file paths.
     *
     * This method performs the following steps:
     * 1. Loads user data from "C:/Users/jwy/Desktop/VirtualBankApplication/src/data/user.txt".
     * 2. Loads account data from "C:/Users/jwy/Desktop/VirtualBankApplication/src/data/account.txt".
     * 3. Loads transaction data from "C:/Users/jwy/Desktop/VirtualBankApplication/src/data/transaction.txt".
     * 4. Loads goal data from "C:/Users/jwy/Desktop/VirtualBankApplication/src/data/goal.txt".
     * 5. Loads parent-kid relationship data from "C:/Users/jwy/Desktop/VirtualBankApplication/src/data/parentkid.txt".
     */
    public static void init() {
        // D:/IdeaProjects/VirtualBankApplication/
        userController.loadUser("src/data/user.txt");
        accountController.loadAccount(userController, "src/data/account.txt");
        transactionController.loadTransaction("src/data/transaction.txt");
        goalController.loadGoal(userController, "src/data/goal.txt");
        userController.loadParentKid("src/data/parentkid.txt");
    }

    /**
     * The main method that serves as the entry point for the Virtual Bank Application.
     *
     * This method calls init() to initialize the application and then
     * starts the UserLoginView to handle user login.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        init();

        UserLoginView userLoginView = new UserLoginView();
        userLoginView.login(userController, accountController, transactionController, goalController);
    }
}
