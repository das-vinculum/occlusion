package org.knittingpatterndesigner.incubator.occlusion.backend;

import com.google.inject.AbstractModule;

/**
 * This is the module encapsulating all the backend parts.
 */
public class BackendModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Storage.class).to(FileStorage.class);
        bind(Backend.class).to(TaskBackend.class);
    }
}
