package controller;

import model.Parent;
import model.Transaction;
import model.User;
import util.TransactionUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Title        : TransactionController.java
 * Description  : This class is responsible for handling transaction-related operations. It manages adding and removing transactions,
 *                retrieving transaction lists by user, filling transaction history tables, loading and saving transaction data from
 *                and to files. The TransactionController interacts with the TransactionUtil to perform file I/O operations.
 * @version     : 1.0
 */
public class TransactionController {
    private TransactionUtil transactionUtil = new TransactionUtil();
    private List<Transaction> transactionList;
    private SimpleDateFormat datetimeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TransactionController() {
        this.transactionList = new ArrayList<>();
    }

    /**
     * Adds a transaction to the transaction list.
     *
     * @param transaction the transaction to be added
     */
    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
        for (int i = 0; i < transactionList.size(); i++) {
            System.out.println(transactionList.get(i));
        }
    }

    /**
     * Removes a transaction from the transaction list.
     *
     * @param transaction the transaction to be removed
     */
    public void removeTransaction(Transaction transaction) {
        transactionList.remove(transaction);
    }
    public List<Transaction> getTransactionListByUser(User searchUser) {
        List<Transaction> userTransactionList = new ArrayList<>();
        for (int i = 0; i < transactionList.size(); i++) {
            Transaction transaction = transactionList.get(i);
            String[] sourceParts = transaction.getSource().trim().split("\\(");
            String[] destinationParts = transaction.getDestination().trim().split("\\(");
            System.out.println(Arrays.toString(sourceParts));
            System.out.println(Arrays.toString(destinationParts));

            if (searchUser.getName().equals(sourceParts[0]) || searchUser.getName().equals(destinationParts[0])) {
                userTransactionList.add(transaction);
            }
        }
        return userTransactionList;
    }
    /**
     * Retrieves a list of transactions associated with a user.
     *
     * @param searchUser the user for whom transactions are to be retrieved
     * @return the list of transactions associated with the user
     */
    public List<Transaction> getKidTransactionListByUser(User searchUser) {
        List<Transaction> userTransactionList = new ArrayList<>();
        List<User> kidList = ((Parent) searchUser).getKidList();
        for (int i = 0; i < transactionList.size(); i++) {
            Transaction transaction = transactionList.get(i);
            for (int j = 0; j < kidList.size(); j++) {
                User currentKid = kidList.get(j);
                String[] sourceParts = transaction.getSource().trim().split("\\(");
                String[] destinationParts = transaction.getDestination().trim().split("\\(");
                if (currentKid.getName().equals(sourceParts[0]) || currentKid.getName().equals(destinationParts[0])) {
                    userTransactionList.add(transaction);
                }
            }
        }
        return userTransactionList;
    }
    public List<Transaction> getTransactionList() {
        return transactionList;
    }
    /**
     * Fills the transaction history table with transaction details.
     *
     * @param transactionHistoryTable the table to be filled with transaction details
     */
    public void fillTransactionHistoryTable(Vector<Vector<String>> transactionHistoryTable, User searchUser) {
        List<Transaction> userTransactionList = getTransactionListByUser(searchUser);
        transactionHistoryTable.clear();
        for (int i = 0; i < userTransactionList.size(); i++) {
            Transaction currentTransaction = userTransactionList.get(i);
            Vector<String> row = new Vector<>();
            row.add(currentTransaction.getSource());
            row.add(currentTransaction.getDestination());
            row.add(currentTransaction.getType());
            row.add("$" + String.format("%.2f", currentTransaction.getTransAmount()));
            row.add(datetimeSdf.format(currentTransaction.getTransTime()));
            row.add(currentTransaction.getRemark());
            transactionHistoryTable.add(row);
        }
    }

    public void fillKidTransactionHistoryTable(Vector<Vector<String>> transactionHistoryTable, User searchUser) {
        List<Transaction> userTransactionList = getKidTransactionListByUser(searchUser);
        transactionHistoryTable.clear();
        for (int i = 0; i < userTransactionList.size(); i++) {
            Transaction currentTransaction = userTransactionList.get(i);
            Vector<String> row = new Vector<>();
            row.add(currentTransaction.getSource());
            row.add(currentTransaction.getDestination());
            row.add(currentTransaction.getType());
            row.add("$" + String.format("%.2f", currentTransaction.getTransAmount()));
            row.add(datetimeSdf.format(currentTransaction.getTransTime()));
            row.add(currentTransaction.getRemark());
            transactionHistoryTable.add(row);
        }
    }
    public void loadTransaction(String filePath) {
        transactionUtil.loadTransactionList(transactionList, filePath);
    }
    /**
     * Saves transaction data to a file.
     *
     * @param filePath the path of the file to save to
     */
    public void saveTransaction(String filePath) {
        transactionUtil.saveTransactionList(transactionList, filePath);
    }
}
