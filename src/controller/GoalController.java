package controller;

import model.Goal;
import model.Parent;
import model.User;
import util.GoalUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Title        : GoalController.java
 * Description  : This class is responsible for managing goals set for kids. It handles adding, removing, and updating goals, as well
 *                as retrieving goal lists by kid, loading and saving goal data from and to files, and filling goal tables for display.
 *                The GoalController interacts with the GoalUtil for file I/O operations.
 * @version     : 1.0
 */
public class GoalController {
    private GoalUtil goalUtil = new GoalUtil();
    private List<Goal> goalList;
    private SimpleDateFormat datetimeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public GoalController() {
        this.goalList = new ArrayList<>();
    }
    /**
     * Adds a goal to the goal list.
     *
     * @param goal the goal to be added
     */
    public void addGoal(Goal goal) {
        goalList.add(goal);
    }
    /**
     * Removes a goal from the goal list.
     *
     * @param goal the goal to be removed
     */
    public void removeGoal(Goal goal) {
        goalList.remove(goal);
    }
    /**
     * Updates the details of an existing goal in the goal list.
     *
     * @param updateGoal the goal with updated details
     */
    public void updateGoal(Goal updateGoal) {
        for (int i = 0; i < goalList.size(); i++) {
            if (goalList.get(i).getName().equals(updateGoal.getName())) {
                goalList.set(i, updateGoal);
                break;
            }
        }
    }
    /**
     * Retrieves a list of goals associated with a kid.
     *
     * @param searchKid the kid for whom goals are to be retrieved
     * @return the list of goals associated with the kid
     */
    public List<Goal> getGoalListByKid(User searchKid) {
        List<Goal> userGoalList = new ArrayList<>();
        for (int i = 0; i < goalList.size(); i++) {
            Goal goal = goalList.get(i);
            User currentKid = goal.getKid();
            if (currentKid.getType().equals(searchKid.getType()) && currentKid.getName().equals(searchKid.getName())) {
                userGoalList.add(goal);
            }
        }
        return userGoalList;
    }
    /**
     * Retrieves a goal from the goal list.
     *
     * @param searchGoal the goal to search for
     * @return the goal if found, null otherwise
     */
    public Goal getGoal(Goal searchGoal) {
        for (int i = 0; i < goalList.size(); i++) {
            Goal goal = goalList.get(i);
            if (goal.getKid() == searchGoal.getKid() && goal.getName().equals(searchGoal.getName())) {
                return goal;
            }
        }
        return null;
    }
    /**
     * Retrieves the list of all goals.
     *
     * @return the list of all goals
     */
    public List<Goal> getGoalList() {
        return goalList;
    }
    /**
     * Loads goal data from a file.
     *
     * @param userController the UserController to resolve user dependencies
     * @param filePath the path of the file to load from
     */
    public void loadGoal(UserController userController, String filePath) {
        goalUtil.loadGoalList(userController, goalList, filePath);
    }
    /**
     * Saves goal data to a file.
     *
     * @param filePath the path of the file to save to
     */
    public void saveGoal(String filePath) {
        goalUtil.saveGoalList(goalList, filePath);
    }
    /**
     * Fills the goal table with goal details associated with a kid.
     *
     * @param goalTable the table to be filled with goal details
     * @param searchKid the kid for whom goals are to be displayed
     */
    public void fillGoalTable(Vector<Vector<String>> goalTable, User searchKid) {
        List<Goal> userGoalList = getGoalListByKid(searchKid);
        goalTable.clear();
        for (int i = 0; i < userGoalList.size(); i++) {
            Goal currentGoal = userGoalList.get(i);
            Vector<String> row = new Vector<>();
            row.add(currentGoal.getKid().getName());
            row.add(currentGoal.getName());
            row.add(currentGoal.getDescription());
            row.add(datetimeSdf.format(currentGoal.getStartTime()));
            row.add(datetimeSdf.format(currentGoal.getDeadline()));
            row.add(currentGoal.getStatus());
            goalTable.add(row);
        }
    }
    /**
     * Fills the task table with goal details associated with the kids of a parent.
     *
     * @param taskTable the table to be filled with goal details
     * @param searchParent the parent whose kids' tasks are to be displayed
     * @return the list of all goals associated with the parent's kids
     */
    public List<Goal> fillKidTaskTable(Vector<Vector<String>> taskTable, User searchParent) {
        taskTable.clear();
        List<User> kidList = ((Parent) searchParent).getKidList();
        List<Goal> kidTaskList = new ArrayList<>();
        for (User kid : kidList) {
            List<Goal> currentKidTaskList = getGoalListByKid(kid);
            kidTaskList.addAll(currentKidTaskList);
        }

        for (int i = 0; i < kidTaskList.size(); i++) {
            Goal currentGoal = kidTaskList.get(i);
            Vector<String> row = new Vector<>();
            row.add(currentGoal.getKid().getName());
            row.add(currentGoal.getName());
            row.add(currentGoal.getDescription());
            row.add(datetimeSdf.format(currentGoal.getStartTime()));
            row.add(datetimeSdf.format(currentGoal.getDeadline()));
            row.add(currentGoal.getStatus());
            taskTable.add(row);
        }
        return kidTaskList;
    }
}
