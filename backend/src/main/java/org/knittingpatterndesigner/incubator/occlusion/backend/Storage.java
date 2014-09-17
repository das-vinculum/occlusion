package org.knittingpatterndesigner.incubator.occlusion.backend;

import java.io.File;
import java.util.List;

/**
 * This interface defines the necessary methods for storing and loading tasks from the background storage.
 */
public interface Storage {
    /**
     * Load the tasks from the given file.
     *
     * @param pathToTaskFile The file which should be loaded.
     * @return A complete list of tasks.
     */
    List<Task> loadTasks(String pathToTaskFile);

    /**
     * This stores the tasks into the file from which they where loaded.
     *
     * @param tasks The list containing the tasks.
     */
    void storeTasksToFile(List<Task> tasks, String pathToFile);

    List<File> getTaskFiles(File folder);
}
