package org.knittingpatterndesigner.incubator.occlusion.backend;

import com.google.inject.Inject;

import java.io.*;
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

    private Storage storage;

    @Inject
    public TaskBackend(Storage storage){
           this.storage = storage;
    }


    @Override
    public void loadTasks(String pathToTaskFile) {
        System.out.println("TaskBackend.loadTasks("+pathToTaskFile+")");
        this.taskLines = this.storage.loadTasks(pathToTaskFile);
        System.out.println("Loaded number of tasks: "+this.taskLines.size());
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


    }
}
