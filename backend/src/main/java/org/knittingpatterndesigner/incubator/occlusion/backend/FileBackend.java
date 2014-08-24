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
    private List<String> taskLines;

    @Override
    public void loadTaskFile(String pathToTaskFile) {

        this.taskFile = new File(pathToTaskFile);

        this.taskLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(taskFile))) {
            String buffer = reader.readLine();
            while (buffer != null) {
                taskLines.add(buffer);
                buffer = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found. " + taskFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("File could not be read. " + taskFile.getAbsolutePath());
        }
    }

    @Override
    public List<String> getTaskLines() {
        return Collections.unmodifiableList(taskLines);
    }

    @Override
    public List<String> getTasksForContext(String context) {

        String searchString;
        if (context.startsWith("@")) {
            searchString = context;
        } else {
            searchString = "@" + context;
        }
        List<String> result = new ArrayList<>();
        for (String task : this.taskLines) {
            if (task.contains(searchString)) {
                result.add(task);
            }
        }
        return result;
    }
}
