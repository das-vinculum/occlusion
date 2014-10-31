package org.knittingpatterndesigner.incubator.occlusion.shell;

import asg.cliche.ShellFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;

/**
 * This class initalizes everything and brings the tool up.
 */
class OcclusionShellStarter {

    public static void main(String[] args) throws IOException {

        Injector injector = Guice.createInjector(new ShellFrontendModule());
        ListTasksCommands commands = injector.getInstance(ListTasksCommands.class);
        commands.loadFiles(args[0]);
        ShellFactory.createConsoleShell("occlusion", "", commands).commandLoop();
    }


}
