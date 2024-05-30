package controller;

import model.Account;
import model.Transaction;
import java.util.Date;

/**
 * Title        : WithdrawController.java
 * Description  : This class is responsible for handling withdrawal operations for accounts. It interacts with the AccountController
 *                to update account balances and with the TransactionController to log withdrawal transactions. The withdraw method
 *                checks if the withdrawal amount is valid and if the account has sufficient balance, then updates the account balance
 *                and logs the transaction with a remark.
 * @version     : 1.0
 */
public class WithdrawController {
    private AccountController accountController = new AccountController();
    /**
     * Handles the withdrawal operation from the specified account.
     *
     * @param transactionController the transaction controller to log the transaction
     * @param account the account from which to withdraw funds
     * @param amount the amount to withdraw
     * @param remark a remark for the transaction
     * @return true if the withdrawal is successful, false otherwise
     */
    public boolean withdraw(TransactionController transactionController, Account account, double amount, String remark) {
        if (amount <= 0 || account.getBalance() < amount) {
            return false;
        }
        account.setBalance(account.getBalance() - amount);
        accountController.updateAccount(account);
        transactionController.addTransaction(new Transaction(account.getUser().getName(), "", "withdraw", amount, new Date(), remark));
        return true;
    }
}
