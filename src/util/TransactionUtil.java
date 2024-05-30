package util;

import controller.UserController;
import model.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
 * Title        : TransactionUtil.java
 * Description  : This class provides utility methods for loading and saving transaction data.
 *                It includes methods to read transaction data from a file and write transaction data to a file.
 * @version     : 1.0
 */
public class TransactionUtil {
    private static SimpleDateFormat datetimeSdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Loads a list of transactions from a specified file.
     *
     * The file should contain transaction data with each transaction on a separate line.
     * Transaction data should be space-separated and follow the format:
     * source destination type transAmount transTime remark
     *
     * @param transactionList the list to populate with transaction data
     * @param filePath the path to the file containing transaction data
     */

    public void loadTransactionList(List<Transaction> transactionList, String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner file = new Scanner(fis);
            while (file.hasNextLine()) {
                String[] parts = file.nextLine().trim().split(" ");
                if (parts.length != 6) {
                    continue;
                }
                String source = parts[0];
                String destination = parts[1];
                String type = parts[2];
                double transAmount = Double.parseDouble(parts[3]);
                Date transTime = datetimeSdf.parse(parts[4]);
                String remark = parts[5];

                Transaction transaction = new Transaction(source, destination, type, transAmount, transTime, remark);
                transactionList.add(transaction);
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
     * Saves a list of transactions to a specified file.
     *
     * Each transaction's data will be written to the file in a format appropriate
     * for later reading by loadTransactionList method.
     *
     * @param transactionList the list of transactions to save
     * @param filePath the path to the file to save transaction data
     */
    public void saveTransactionList(List<Transaction> transactionList, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < transactionList.size(); i++) {
                Transaction currentAccount = transactionList.get(i);
                fw.write(currentAccount + "\n");
            }
            fw.close();
            System.out.println("Save file " + filePath + " successfully!");
        } catch (IOException e) {
            System.out.println("File " + filePath + " does not exist! ");
        }
    }
}
