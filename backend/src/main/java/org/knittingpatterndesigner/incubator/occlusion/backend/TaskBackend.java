package org.knittingpatterndesigner.incubator.occlusion.backend;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 23.08.14
 */
public class TaskBackend implements Backend {


    private List<Task> taskLines;

    private List<Task> contexts;

    private Storage storage;

    public void setTaskFolder(String taskFolder) {
        this.taskFolder = taskFolder;
    }

    private String taskFolder;

    @Inject
    public TaskBackend(Storage storage) {
        this.storage = storage;
    }


    @Override
    public void loadTasks() {
        this.taskLines = this.storage.loadTasks(taskFolder + "/todo.txt");
        this.contexts = this.storage.loadTasks(taskFolder + "/contexts.txt");
    }

    @Override
    public List<Task> getTaskLines() {
        return makeImmutable(taskLines);
    }

    private List<Task> makeImmutable(List<Task> tasks) {
        return Collections.unmodifiableList(tasks);
    }

    @Override
    public List<Task> getTasksForContext(String context) {

        List<Task> result = new ArrayList<>();
        for (Task task : this.taskLines) {
            if (task.isContext(context)) {
                result.add(task);
            }
        }
        return makeImmutable(result);
    }

    @Override
    public List<Task> getTasksForProject(String project) {

        List<Task> result = new ArrayList<>();
        for (Task task : this.taskLines) {
            if (task.isProject(project)) {
                result.add(task);
            }
        }
        return makeImmutable(result);
    }

    @Override
    public void addTask(Task task) {

        this.taskLines.add(task);
        this.storage.storeTasksToFile(taskLines, taskFolder + "/todo.txt");
    }

    @Override
    public List<Task> getContexts() {

        return makeImmutable(contexts);
    }
}
