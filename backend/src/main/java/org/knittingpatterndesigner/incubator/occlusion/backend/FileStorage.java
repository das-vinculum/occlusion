package org.knittingpatterndesigner.incubator.occlusion.backend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 03.09.14
 */
public class FileStorage implements Storage {

    @Override
    public List<Task> loadTasks(String pathToTaskFile) {
        List<Task> taskLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToTaskFile))) {
            String buffer = reader.readLine();
            while (buffer != null) {
                taskLines.add(new Task(buffer));
                buffer = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found. " + pathToTaskFile);
        } catch (IOException e) {
            System.out.println("File could not be read. " + pathToTaskFile);
        }

        return taskLines;
    }

}
