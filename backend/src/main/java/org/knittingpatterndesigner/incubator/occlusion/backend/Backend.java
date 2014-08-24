package org.knittingpatterndesigner.incubator.occlusion.backend;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 24.08.14
 */
public interface Backend {
    void loadTaskFile(String pathToTaskFile);

    List<String> getTaskLines();

    List<String> getTasksForContext(String context);
}