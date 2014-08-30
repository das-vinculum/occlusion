package org.knittingpatterndesigner.incubator.occlusion.backend;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 23.08.14
 */
public class FileBackend implements Backend {

    private File taskFile;
    private List<Task> taskLines;

    @Override
    public void loadTaskFile(String pathToTaskFile) {

        this.taskFile = new File(pathToTaskFile);
        this.taskLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(taskFile))) {
            String buffer = reader.readLine();
            while (buffer != null) {
                taskLines.add(new Task(buffer));
                buffer = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found. " + taskFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("File could not be read. " + taskFile.getAbsolutePath());
        }
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

        String searchString;
        if (context.startsWith("@")) {
            searchString = context;
        } else {
            searchString = "@" + context;
        }
        List<Task> result = new ArrayList<>();
        for (Task task : this.taskLines) {
            if (task.isContext(searchString)) {
                result.add(task);
            }
        }
        return makeImmutable(result);
    }

    @Override
    public List<Task> getTasksForProject(String project) {

        String searchString;
        if (project.startsWith("+")) {
            searchString = project;
        } else {
            searchString = "+" + project;
        }
        List<Task> result = new ArrayList<>();
        for (Task task : this.taskLines) {
            if (task.isProject(searchString)) {
                result.add(task);
            }
        }
        return makeImmutable(result);
    }
}
