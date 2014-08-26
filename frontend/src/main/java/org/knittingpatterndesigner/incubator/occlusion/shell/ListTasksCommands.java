package org.knittingpatterndesigner.incubator.occlusion.shell;

import asg.cliche.Command;
import asg.cliche.Param;
import org.knittingpatterndesigner.incubator.occlusion.backend.Backend;

import java.util.List;


/**
 *
 * Main starting point for the shell interface
 */
public class ListTasksCommands {

    private Backend backend;

    public ListTasksCommands(Backend backend) {

        this.backend = backend;
    }

    public void loadFile(String filename){
        this.backend.loadTaskFile(filename);
    }

    @Command(name = "list-tasks", abbrev = "l", description = "This lists all tasks stored in your todo.txt.")
    public String listTasks() {

        return prepareListOfTasksForPrintingOnScreen(this.backend.getTaskLines());
    }

    @Command(name = "list-tasks-by-context", abbrev = "lc", description = "This command lists all Tasks which belong to the given context.")
    public String listTasksByContext(@Param(name = "Context", description = "The context you want to list the tasks for.") String context) {

        return prepareListOfTasksForPrintingOnScreen(this.backend.getTasksForContext(context));
    }

    private String prepareListOfTasksForPrintingOnScreen(List<String> taskLines) {
        StringBuffer result = new StringBuffer();
        for (String line : taskLines) {

            result.append(line).append("\n");
        }
        return result.toString();
    }

}
