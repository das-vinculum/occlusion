package org.knittingpatterndesigner.incubator.occlusion.shell;

import asg.cliche.ShellFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.knittingpatterndesigner.incubator.occlusion.backend.BackendModule;
import org.knittingpatterndesigner.incubator.occlusion.backend.TaskBackend;

import java.io.IOException;

/**
 * This class initalizes everything and brings the tool up.
 */
public class OcclusionShellStarter {

    public static void main(String[] args) throws IOException {

        Injector injector = Guice.createInjector(new BackendModule(), new ShellFrontendModule());
        injector.getInstance(ListTasksCommands.class).loadFile(args[0]);
        ShellFactory.createConsoleShell("occlusion", "", new ListTasksCommands(injector.getInstance(TaskBackend.class))).commandLoop();
    }


}
