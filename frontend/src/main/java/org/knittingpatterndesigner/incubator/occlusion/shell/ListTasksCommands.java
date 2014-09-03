package org.knittingpatterndesigner.incubator.occlusion.shell;

import asg.cliche.Command;
import asg.cliche.Param;
import com.google.inject.Inject;
import org.knittingpatterndesigner.incubator.occlusion.backend.Backend;
import org.knittingpatterndesigner.incubator.occlusion.backend.Task;

import java.util.List;


/**
 * Main starting point for the shell interface
 */
public class ListTasksCommands {

    private Backend backend;

    @Inject
    public ListTasksCommands(Backend backend) {

        this.backend = backend;
    }

    public void loadFile(String filename) {
        this.backend.loadTasks(filename);
    }

    @Command(name = "list-tasks", abbrev = "l", description = "This lists all tasks stored in your todo.txt.")
    public String listTasks() {

        return prepareListOfTasksForPrintingOnScreen(this.backend.getTaskLines());
    }

    @Command(name = "list-tasks-by-context", abbrev = "lc", description = "This command lists all Tasks which belong to the given context.")
    public String listTasksByContext(@Param(name = "Context", description = "The context you want to list the tasks for.") String context) {

        return prepareListOfTasksForPrintingOnScreen(this.backend.getTasksForContext(context));
    }

    private String prepareListOfTasksForPrintingOnScreen(List<Task> taskLines) {
        StringBuffer result = new StringBuffer();
        for (Task line : taskLines) {

            result.append(line.getOriginalLine()).append("\n");
        }
        return result.toString();
    }


    public String listTasksByProject(String project) {

        return prepareListOfTasksForPrintingOnScreen(this.backend.getTasksForProject(project));
    }

    public void add(String... task) {

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String word : task) {
            if (i > 0) {
                builder.append(" ");
            }
            i++;
            builder.append(word);
        }
        Task taskObject = new Task(builder.toString());
        backend.addTask(taskObject);
    }
}
