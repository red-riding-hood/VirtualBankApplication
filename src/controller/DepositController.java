package controller;

import model.Account;
import model.Transaction;
import java.util.Date;

/**
 * Title        : DepositController.java
 * Description  : This class is responsible for handling deposit operations for accounts. It interacts with the AccountController
 *                to update account balances and with the TransactionController to log deposit transactions. The deposit method
 *                checks if the deposit amount is valid, then updates the account balance and logs the transaction with a remark.
 * @version     : 1.0
 */
public class DepositController {
    private AccountController accountController = new AccountController();

    /**
     * Handles the deposit of funds into an account.
     *
     * @param transactionController the transaction controller to log the transaction
     * @param account the account to deposit funds into
     * @param amount the amount to deposit
     * @param remark a remark for the transaction
     * @return true if the deposit is successful, false otherwise
     */
    public boolean deposit(TransactionController transactionController, Account account, double amount, String remark) {
        if (amount <= 0) {
            return false;
        }
        account.setBalance(account.getBalance() + amount);
        accountController.updateAccount(account);
        transactionController.addTransaction(new Transaction(account.getUser().getName(), "", "deposit", amount, new Date(), remark));
        return true;
    }
}
