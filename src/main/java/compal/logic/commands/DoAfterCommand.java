package compal.logic.commands;

import compal.logic.parser.CommandParser;
import compal.main.Duke;
import compal.tasks.DoAfterTasks;
import compal.tasks.Event;
import compal.tasks.TaskList;

import java.text.ParseException;
import java.util.Scanner;

public class DoAfterCommand extends Command implements CommandParser {

    private final String TOKEN = "/after";
    private TaskList taskList;

    public DoAfterCommand(Duke d) {
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
        String event = scanner.next();
        if (scanner.hasNext()) {
            String restOfInput = scanner.nextLine();
            String description = getDescription(restOfInput);
            String date = getDate(restOfInput);
            taskList.addTask(new DoAfterTasks(description, date));
            int arrSize = taskList.arrlist.size()-1;
            String statusIcon = taskList.arrlist.get(arrSize).getStatusIcon();
            duke.ui.printg("[DAT][" + statusIcon + "] " + description);
        } else {
            throw new Duke.DukeException(sadFace + " OOPS!!! The description of a " + event + " cannot be empty.");
        }
    }
}