package org.knittingpatterndesigner.incubator.occlusion.backend;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 24.08.14
 */
public interface Backend {

    void loadTasks(String pathToTaskFile);

    List<Task> getTaskLines();

    List<Task> getTasksForContext(String context);

    List<Task> getTasksForProject(String preis_db);

    void addTask(Task expectedTask);
}
