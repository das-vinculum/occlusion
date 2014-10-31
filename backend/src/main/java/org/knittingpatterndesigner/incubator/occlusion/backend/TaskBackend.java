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

    private List<Task> done;

    private final Storage storage;
    private String taskFolder;

    @Inject
    public TaskBackend(Storage storage) {
        this.storage = storage;
    }

    public void setTaskFolder(String taskFolder) {
        this.taskFolder = taskFolder;
    }

    @Override
    public void loadTasks() {
        this.taskLines = this.storage.loadTasks(getPathToTodoFile());
        this.contexts = this.storage.loadTasks(getPathToContextsFile());
        this.done = this.storage.loadTasks(getPathToDoneFile());
    }

    private String getPathToDoneFile() {
        return taskFolder + "/done.txt";
    }

    private String getPathToContextsFile() {
        return taskFolder + "/contexts.txt";
    }

    private String getPathToTodoFile() {
        return taskFolder + "/todo.txt";
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
        this.storage.storeTasksToFile(taskLines, getPathToTodoFile());
    }

    @Override
    public List<Task> getContexts() {

        return makeImmutable(contexts);
    }

    @Override
    public void markTaskAsDone(int taskLineNumber) {
        for (int i = 0; i < taskLines.size(); i++) {
            if (taskLines.get(i).getLinenumber() == taskLineNumber) {
                Task taskToBeMarked = this.taskLines.get(i);
                this.taskLines.remove(i);
                taskToBeMarked.setLinenumber(done.size());
                done.add(taskToBeMarked);
                this.storage.storeTasksToFile(taskLines, getPathToTodoFile());
                this.storage.storeTasksToFile(done, getPathToDoneFile());
                break;
            }
        }
    }

    @Override
    public List<Task> getDone() {
        return makeImmutable(done);
    }
}
