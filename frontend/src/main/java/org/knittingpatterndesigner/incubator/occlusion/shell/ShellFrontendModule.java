package org.knittingpatterndesigner.incubator.occlusion.shell;

import com.google.inject.AbstractModule;
import org.knittingpatterndesigner.incubator.occlusion.backend.Backend;
import org.knittingpatterndesigner.incubator.occlusion.backend.FileStorage;
import org.knittingpatterndesigner.incubator.occlusion.backend.Storage;
import org.knittingpatterndesigner.incubator.occlusion.backend.TaskBackend;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 26.08.14
 */
public class ShellFrontendModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(Storage.class).to(FileStorage.class);
        bind(Backend.class).to(TaskBackend.class);
        bind(ListTasksCommands.class);

    }
}
