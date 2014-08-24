package org.knittingpatterndesigner.incubator.occlusion.shell;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.ShellFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.knittingpatterndesigner.incubator.occlusion.backend.Backend;
import org.knittingpatterndesigner.incubator.occlusion.backend.BackendModule;
import org.knittingpatterndesigner.incubator.occlusion.backend.FileBackend;

import java.io.IOException;


/**
 * Main starting point for the shell interface
 */
public class OcclusionShell {

    private Backend backend;

    public OcclusionShell(Backend backend, String pathToTaskFile) {

        this.backend = backend;
        this.backend.loadTaskFile(pathToTaskFile);
    }

    public static void main(String[] args) throws IOException {

        Injector injector = Guice.createInjector(new BackendModule());
        ShellFactory.createConsoleShell("occlusion", "", new OcclusionShell(injector.getInstance(FileBackend.class), args[0])).commandLoop();
    }

    @Command(name ="list-tasks", abbrev = "l",description = "This lists all tasks stored in your todo.txt.")
    public String listTasks() {

        StringBuffer result = new StringBuffer();
        for (String line : this.backend.getTaskLines()) {

            result.append(line).append("\n");
        }
        return result.toString();
    }

    @Command(name = "list-tasks-by-context", abbrev = "lc",description = "This command lists all Tasks which belong to the given context.")
    public String listTasksByContext(@Param(name = "Context",description = "The context you want to list the tasks for.")String context) {

        StringBuffer result = new StringBuffer();

        for (String line : this.backend.getTasksForContext(context)) {

            result.append(line).append("\n");
        }
        return result.toString();
    }

}
