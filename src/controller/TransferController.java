package controller;

import model.Account;
import model.Transaction;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Title        : TransferController.java
 * Description  : This class is responsible for handling transfer operations between accounts. It interacts with the AccountController
 *                to update account balances and with the TransactionController to log transfer transactions. The transfer method
 *                checks if the transfer amount is valid and if the source account has sufficient balance, then updates the balances
 *                of both source and destination accounts and logs the transaction with a remark.
 * @version     : 1.0
 */
public class TransferController {
    private AccountController accountController = new AccountController();

    public boolean isSavingAccount(Account account) {
        return "Saving".equals(account.getType());
    }
    /**
     * Handles the transfer of funds between two accounts.
     *
     * @param transactionController the transaction controller to log the transaction
     * @param accountFrom the source account from which funds are to be transferred
     * @param accountTo the destination account to which funds are to be transferred
     * @param amount the amount to transfer
     * @param remark a remark for the transaction
     * @return true if the transfer is successful, false otherwise
     */
    public boolean transfer(TransactionController transactionController, Account accountFrom, Account accountTo, double amount, String remark) {
        if (amount <= 0 || accountFrom.getBalance() < amount) {
            JOptionPane.showMessageDialog(null, "Not enough money, transfer failed！", "Oops...", 2);
            return false;
        }

        if (isSavingAccount(accountFrom)) {
            JOptionPane.showMessageDialog(null, "Transfers cannot be made from your savings account！", "Oops...", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (isSavingAccount(accountTo)) {
            JOptionPane.showMessageDialog(null, "Unable to transfer funds to savings account！", "Oops...", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountTo.setBalance(accountTo.getBalance() + amount);
        accountController.updateAccount(accountFrom);
        accountController.updateAccount(accountTo);
        transactionController.addTransaction(new Transaction(accountFrom.getUser().getName(), accountTo.getUser().getName(), "transfer", amount, new Date(), remark));
        return true;
    }
}

