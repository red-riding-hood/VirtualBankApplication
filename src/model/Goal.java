package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title        : Goal.java
 * Description  : This class represents a goal entity in the system. It includes properties such as the kid associated with the goal,
 *                the name and description of the goal, the start time and deadline of the goal, and the status of the goal.
 *                It provides a user-defined constructor to initialize the Goal object with these attributes. Getters and setters are
 *                provided for each property to allow for their retrieval and modification. The class also overrides the toString
 *                method to provide a string representation of the Goal object, including formatted date and time.
 * @version     : 1.0
 */
public class Goal {
    private User kid;
    private String name;
    private String description;
    private Date startTime;
    private Date deadline;
    private String status;

    public Goal(User kid, String name, String description, Date startTime, Date deadline, String status) {
        this.kid = kid;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.deadline = deadline;
        this.status = status;
    }

    // Getters and setters
    public User getKid() {
        return kid;
    }

    public void setKid(User kid) {
        this.kid = kid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // String representation
    @Override
    public String toString() {
        SimpleDateFormat datetimeSdf = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
        return kid.getName() + " " + name + " " + description + " " +
                datetimeSdf.format(startTime) + " " + datetimeSdf.format(deadline) + " " +
                status;
    }
}
