package org.knittingpatterndesigner.incubator.occlusion.backend;

import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 24.08.14
 */
public class BackendModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Backend.class).to(FileBackend.class);
    }
}
