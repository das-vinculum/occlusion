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
class ListTasksCommands {

    private final Backend backend;

    @Inject
    public ListTasksCommands(Backend backend) {

        this.backend = backend;
    }

    public void loadFiles(String folder) {
        this.backend.setTaskFolder(folder);
        this.backend.loadTasks();
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

            result.append(line.getLinenumber()).append(':').append(' ').append(line.getOriginalLine()).append("\n");
        }
        return result.toString();
    }

    @Command(name = "list-tasks-by-project", abbrev = "lp", description = "This command lists all tasks matching the given project.")
    public String listTasksByProject(String project) {

        return prepareListOfTasksForPrintingOnScreen(this.backend.getTasksForProject(project));
    }

    @Command(name = "add-task", abbrev = "a", description = "This command adds a new task to the file.")
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

    @Command(name = "list-contexts", abbrev = "lcs", description = "This lists all contexts available.")
    public String listContexts() {
        return prepareListOfTasksForPrintingOnScreen(this.backend.getContexts());
    }

    @Command(name = "mark-task-done", abbrev = "do", description = "This marks a task as being done")
    public String markTaskAsDone(int lineNumber) {
        this.backend.markTaskAsDone(lineNumber);
        return "Task " + lineNumber + " has been marked as done.";
    }
}
