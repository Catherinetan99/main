package compal.logic.commands;

import compal.logic.parser.CommandParser;
import compal.main.Duke;
import compal.tasks.RecurringTask;
import compal.tasks.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class RecurTaskCommand extends Command implements CommandParser {

    private static final String TOKEN_REP = "/rep";
    private final String TOKEN = "/by";
    private TaskList taskList;

    public RecurTaskCommand(Duke d) {
        super(d);
        this.taskList = d.tasklist;
    }

    /**
     * Adds a single ToDo to the tasklist and print out confirmation for the user.
     *
     * @param userIn Entire String input by the user.
     */
    @Override
    public void Command(String userIn) throws Duke.DukeException {
        Scanner scanner = new Scanner(userIn);
        String recurtask = scanner.next();
        if (scanner.hasNext()) {
            String restOfInput = scanner.nextLine();
            String description = getDescription(restOfInput);
            String startDate = getDate(restOfInput);
            int rep = getRep(restOfInput);
            for (int count = 0; count < rep; count++) {
                String date = startDate;
                taskList.addTask(new RecurringTask(description, date));
                int arrSize = taskList.arrlist.size()-1;
                String statusIcon = taskList.arrlist.get(arrSize).getStatusIcon();
                duke.ui.printg("[RT][" + statusIcon + "] " + description);
                incrementDateByWeek(date);
            }

        } else {
            throw new Duke.DukeException(sadFace + " OOPS!!! The description of a " + recurtask + " cannot be empty.");
        }
    }

    /**
     * This function returns the number of repetitions of the recurring task in an integer form.
     *
     * @param restOfUserInput description string
     * @return The number of repetitions of the recurring task.
     * @UsedIn recurTaskPacker
     */
    public static int getRep(String restOfUserInput) {
        int splitPoint = restOfUserInput.indexOf(TOKEN_REP);
        String repPart = restOfUserInput.substring(splitPoint);
        Scanner sc = new Scanner(repPart);
        sc.next();
        int repNum = sc.nextInt();
        return repNum;
    }

    /**
     * Increment the date by one week
     *
     * @param dateString The date to be incremented
     * @return The final incremented date.
     * @UsedIn recurTaskPacker
     */
    public static Date incrementDateByWeek(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 7);
        return calendar.getTime();
    }
}