package controller;

import model.Account;
import model.User;
import util.AccountUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Title        : AccountController.java
 * Description  : This class manages accounts, including adding, removing, and updating accounts, retrieving account lists by user,
 *                loading and saving account data from and to files, and getting the balance of an account. It interacts with the
 *                AccountUtil for file I/O operations.
 * @version     : 1.0
 */
public class AccountController {
    private AccountUtil accountUtil = new AccountUtil();
    private List<Account> accountList;

    public Account getType(String accountName, User user) {
        List<Account> accountList = getAccountListByUser(user);
        for (Account account : accountList) {
            if ((account.getAccountName() + " (余额: " + String.format("%.2f", account.getBalance()) + ")").equals(accountName)) {
                return account;
            }
        }
        return null;
    }
    /**
     * Initializes an AccountController object.
     */
    public AccountController() {
        this.accountList = new ArrayList<>();
    }

    /**
     * Adds an account to the account list.
     *
     * @param account the account to be added
     */
    public void addAccount(Account account) {
        accountList.add(account);
    }

    /**
     * Removes an account from the account list.
     *
     * @param account the account to be removed
     */
    public void removeAccount(Account account) {
        accountList.remove(account);
    }

    /**
     * Updates the details of an existing account in the account list.
     *
     * @param updateAccount the account with updated details
     */
    public void updateAccount(Account updateAccount) {
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccountName().equals(updateAccount.getAccountName())) {
                accountList.set(i, updateAccount);
                break;
            }
        }
    }

    /**
     * Retrieves a list of accounts associated with a user.
     *
     * @param searchUser the user for whom accounts are to be retrieved
     * @return the list of accounts associated with the user
     */
    public List<Account> getAccountListByUser(User searchUser) {
        List<Account> userAccountList = new ArrayList<>();
        for (int i = 0; i < accountList.size(); i++) {
            Account account = accountList.get(i);
            User currentUser = account.getUser();
            if (currentUser.getType().equals(searchUser.getType()) && currentUser.getName().equals(searchUser.getName())) {
                userAccountList.add(account);
            }
        }
        return userAccountList;
    }

    /**
     * Retrieves an account from the account list.
     *
     * @param searchAccount the account to search for
     * @return the account if found, null otherwise
     */
    public Account getAccount(Account searchAccount) {
        for (int i = 0; i < accountList.size(); i++) {
            Account account = accountList.get(i);
            if (account.getAccountName().equals(searchAccount.getAccountName())) {
                return account;
            }
        }
        return null;
    }

    /**
     * Retrieves the list of all accounts.
     *
     * @return the list of all accounts
     */
    public List<Account> getAccountList() {
        return accountList;
    }

    /**
     * Retrieves the balance of an account.
     *
     * @param account the account whose balance is to be retrieved
     * @return the balance of the account
     */
    public double getBalance(Account account) {
        return account.getBalance();
    }



    public void fillAccountInfoTable(Vector<Vector<String>> accountInfoTable, User currentUser) {
        accountInfoTable.clear();
        List<Account> userAccountList = getAccountListByUser(currentUser);
        for (int i = 0; i < userAccountList.size(); i++) {
            Account userAccount = userAccountList.get(i);
            Vector<String> row = new Vector<>();
            row.add(userAccount.getType());
            row.add(userAccount.getUser().getName());
            row.add(userAccount.getAccountName());
            row.add(String.valueOf(userAccount.getCreateTime()));
            row.add(String.valueOf(userAccount.getBalance()));
            accountInfoTable.add(row);
        }




    }
    public void loadAccount(UserController userController, String filePath) {
        accountUtil.loadAccountList(userController, accountList, filePath);
    }

    /**
     * Saves account data to a file.
     *
     * @param filePath the path of the file to save to
     */
    public void saveAccount(String filePath) {
        accountUtil.saveAccountList(accountList, filePath);
    }

}
