package org.knittingpatterndesigner.incubator.occlusion.shell;

import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 26.08.14
 */
public class ShellFrontendModule extends AbstractModule{



    @Override
    protected void configure() {

        bind(ListTasksCommands.class);

    }
}
