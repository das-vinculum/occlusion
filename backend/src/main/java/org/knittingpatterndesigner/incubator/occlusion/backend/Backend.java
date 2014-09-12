package org.knittingpatterndesigner.incubator.occlusion.backend;

import java.util.List;

/**
 * This interface defines the necessary functions necessary to work with the lists.
 */
public interface Backend {

    /**
     * This function loads the files from the filesystem.
     *
     * @param pathToTaskFile The place of the file.
     */
    void loadTasks(String pathToTaskFile);

    /**
     * This returns the tasks
     *
     * @return A unchangeable implementation of a list of tasks.
     */
    List<Task> getTaskLines();

    /**
     * This returns the tasks matching the context.
     *
     * @param context The context for which the tasks should be filtered.
     * @return A unchangeable implementation of a list of tasks.
     */
    List<Task> getTasksForContext(String context);

    /**
     * This returns the tasks matching the project.
     *
     * @param project The project for which the tasks should be filtered.
     * @return A unchangeable implementation of a list of tasks.
     */
    List<Task> getTasksForProject(String project);

    /**
     * This adds a task to the list
     *
     * @param task The task which should be added.
     */
    void addTask(Task task);
}
