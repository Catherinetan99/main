package compal.tasks;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents task with description, status and reminder.
 */
public abstract class Task implements Serializable {

    //***Class Properties/Variables***--------------------------------------------------------------------------------->
    public boolean isDone;
    protected String symbol;
    private int id;

    //For now, we only process dates in the format dd/mm/yyyy hhmm. See TaskList class for details.
    private Date date;
    private Date time;
    private String taskType;
    private String description;
    private Integer durationHour;
    private Integer durationMinute;
    private boolean hasReminder;
    //----------------------->


    //***CONSTRUCTORS***------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------->

    /**
     * Constructs Task object.
     *
     * @param description Description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        hasReminder = false;
    }
    //----------------------->

    //***GETTER FUNCTIONS***--------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------->

    /**
     * Gets status icon (tick or cross) of task.
     *
     * @return Status icon (tick or cross) of task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Gets symbol of task.
     *
     * @return Symbol of task.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets date of task in date format.
     *
     * @return Date of task.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Gets date of task in string.
     *
     * @return Date of task.
     */
    public String getStringDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = formatter.format(this.date);
        return stringDate;
    }

    /**
     * Gets durationHour of task.
     *
     * @return Hour duration of task.
     */
    public Integer getDurationHour() {
        return durationHour;
    }

    /**
     * Gets durationMinute of task.
     *
     * @return Minute duration of task.
     */
    public Integer getDurationMinute() {
        return durationMinute;
    }

    /**
     * Gets hasReminder of task.
     *
     * @return whether the task has reminder. If task has reminder, return true.
     * If task has no reminder, return false.
     */
    public boolean isHasReminder() {
        return hasReminder;
    }

    /**
     * Gets time of task in date format.
     *
     * @return Time of task.
     */
    public Date getTime() {
        return time;
    }

    /**
     * Gets time of task in string.
     *
     * @return Time of task.
     */
    public String getStringTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
        String stringDate = formatter.format(this.time);
        return stringDate;
    }

    /**
     * Gets description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }
    //----------------------->

    //***SETTER FUNCTIONS***--------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------->
    /**
     * Sets durationHour as input durationHour.
     *
     * @param durationHour Input duration hour.
     */
    public void setDurationHour(Integer durationHour) {
        this.durationHour = durationHour;
    }

    /**
     * Sets durationMinute as input durationMinute.
     *
     * @param durationMinute Input duration minute.
     */
    public void setDurationMinute(Integer durationMinute) {
        this.durationMinute = durationMinute;
    }

    /**
     * Formats dateInput then sets date as dateInput.
     *
     * @param dateInput Input date of task.
     */
    public void setDate(String dateInput) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(dateInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.date = date;
    }

    /**
     * Formats timeInput then sets time as timeInput.
     *
     * @param timeInput Input time of task.
     */
    public void setTime(String timeInput) {
        SimpleDateFormat format = new SimpleDateFormat("HHmm");
        Date time = null;
        try {
            time = format.parse(timeInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.time = time;
    }

    /**
     * Sets isDone as true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets HasReminder as true.
     */
    public void setHasReminder() {
        this.hasReminder = true;
    }
    //----------------------->

    /**
     * Returns the task as a formatted string.
     * This function standardizes the displayed task format.
     *
     * @return Task as a formatted string.
     */
    @Override
    public String toString() {
        if (getDurationHour() != null && getDurationMinute() != null) {
            return "[" + getSymbol() + "]" + "[" + getStatusIcon() + "] " + getDescription() + " Date: " + getStringDate() + " Hour: " + getDurationHour() + " Min: " + getDurationMinute();
        }
        if (getTime() == null) {
            return "[" + getSymbol() + "]" + "[" + getStatusIcon() + "] " + getDescription() + " Date: " + getStringDate();
        }
        return "[" + getSymbol() + "]" + "[" + getStatusIcon() + "] " + getDescription() + " Date: " + getStringDate() + " Time: " + getStringTime();
    }
}
