package util;

import controller.UserController;
import model.Account;
import model.CurrentAccount;
import model.SavingAccount;
import model.User;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Title        : AccountUtil.java
 * Description  : This class provides utility methods for loading and saving account data.
 *                It includes methods to read account data from a file and write account data to a file.
 * @version     : 1.0
 */
public class AccountUtil {
    private static SimpleDateFormat datetimeSdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Loads a list of accounts from a specified file.
     *
     * The file should contain account data with each account on a separate line.
     * Account data should be space-separated and follow the format:
     * type userName accountName createTime balance
     *
     * @param userController the user controller to retrieve User objects by name
     * @param accountList the list to populate with account data
     * @param filePath the path to the file containing account data
     */
    public void loadAccountList(UserController userController, List<Account> accountList, String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner file = new Scanner(fis);
            while (file.hasNextLine()) {
                String[] parts = file.nextLine().trim().split(" ");
                if (parts.length != 5) {
                    continue;
                }
                String type = parts[0];
                User user = userController.getUserByName(parts[1]);
                String accountName = parts[2];
                Date createTime = datetimeSdf.parse(parts[3]);
                double balance = Double.parseDouble(parts[4]);
                if (user == null) {
                    continue;
                }

                // Set fixed overdraftAmount = 1000.00, and interestRate = 0.05(5%)
                Account account = null;
                if (type.equals("Current")) {
                    account = new CurrentAccount(type, user, accountName, createTime, balance, 1000.00);
                } else if (type.equals("Saving")) {
                    account = new SavingAccount(type, user, accountName, createTime, balance, 0.05);
                }
                accountList.add(account);
            }
            file.close();
            fis.close();
            System.out.println("Read file " + filePath + " successfully!");
        } catch (IOException e) {
            System.out.println("File does not exist! ");
        } catch (ParseException e) {
            System.out.println("Invalid data format! ");
        }
    }

    /**
     * Saves a list of accounts to a specified file.
     *
     * Each account's data will be written to the file in a format appropriate
     * for later reading by loadAccountList method.
     *
     * @param accountList the list of accounts to save
     * @param filePath the path to the file to save account data
     */
    public void saveAccountList(List<Account> accountList, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < accountList.size(); i++) {
                Account currentAccount = accountList.get(i);
                fw.write(currentAccount + "\n");
            }
            fw.close();
            System.out.println("Save file " + filePath + " successfully!");
        } catch (IOException e) {
            System.out.println("File " + filePath + " does not exist! ");
        }
    }
}
