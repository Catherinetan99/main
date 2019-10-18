package compal.ui;

import compal.logic.LogicManager;
import compal.logic.command.exceptions.CommandException;
import compal.logic.parser.exceptions.ParserException;
import compal.model.tasks.Task;
import compal.model.tasks.TaskList;
import compal.storage.TaskStorageManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

/**
 * Handles GUI.
 * This is a JavaFXML Controller class.
 */
public class MainWindow extends AnchorPane {
    //Class Properties/Variables
    private LogicManager logicManager;
    private TaskStorageManager taskStorageManager;

    private ArrayList<Task> taskArrList;
    private TaskList taskList;

    @FXML
    private TextField userInput;

    /**
     * Main window constructor.
     */
    public MainWindow() {
        this.taskStorageManager = new TaskStorageManager();
        this.logicManager = new LogicManager();
        this.taskArrList = new ArrayList<>();

    }

    /**
     * Handles user input by sending it to the parser.
     * Called by the enter button inside MainWindow.fxml.
     */
    @FXML
    private void handleUserInput() throws ParserException, CommandException {
        String cmd = userInput.getText();
        init();

        logicManager.logicExecute(cmd, taskList);
        userInput.clear();
    }

    private void init() {
        taskArrList = taskStorageManager.loadData();
        this.taskList = new TaskList();
        this.taskList.setArrList(taskArrList);
    }


}