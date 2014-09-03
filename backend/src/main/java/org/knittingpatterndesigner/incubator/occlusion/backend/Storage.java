package org.knittingpatterndesigner.incubator.occlusion.backend;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 03.09.14
 */
public interface Storage {
    List<Task> loadTasks(String pathToTaskFile);

    void storeTasks(List<Task> tasks);
}
