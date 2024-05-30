package util;

import controller.GoalController;
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
 * Title        : GoalUtil.java
 * Description  : This class provides utility methods for loading and saving goal data.
 *                It includes methods to read goal data from a file and write goal data to a file.
 * @version     : 1.0
 */
public class GoalUtil {
    private static SimpleDateFormat datetimeSdf = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");


    /**
     * Loads a list of goals from a specified file.
     *
     * The file should contain goal data with each goal on a separate line.
     * Goal data should be space-separated and follow the format:
     * kidName goalName description startTime deadline status
     *
     * @param userController the user controller to retrieve User objects by name
     * @param goalList the list to populate with goal data
     * @param filePath the path to the file containing goal data
     */
    public void loadGoalList(UserController userController, List<Goal> goalList, String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner file = new Scanner(fis);
            while (file.hasNextLine()) {
                String[] parts = file.nextLine().trim().split(" ");
                if (parts.length != 6) {
                    continue;
                }
                User kid = userController.getUserByName(parts[0]);
                String name = parts[1];
                String description = parts[2];
                Date startTime = datetimeSdf.parse(parts[3]);
                Date deadline = datetimeSdf.parse(parts[4]);
                String status = parts[5];
                Goal goal = new Goal(kid, name, description, startTime, deadline, status);
                goalList.add(goal);
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
     * Saves a list of goals to a specified file.
     *
     * Each goal's data will be written to the file in a format appropriate
     * for later reading by loadGoalList method.
     *
     * @param goalList the list of goals to save
     * @param filePath the path to the file to save goal data
     */
    public void saveGoalList(List<Goal> goalList, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < goalList.size(); i++) {
                Goal currentGoal = goalList.get(i);
                fw.write(currentGoal + "\n");
            }
            fw.close();
            System.out.println("Save file " + filePath + " successfully!");
        } catch (IOException e) {
            System.out.println("File " + filePath + " does not exist! ");
        }
    }
}
