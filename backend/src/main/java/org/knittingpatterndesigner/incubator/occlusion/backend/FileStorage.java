package org.knittingpatterndesigner.incubator.occlusion.backend;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a simple store using one file in the file system.
 */
public class FileStorage implements Storage {


    @Override
    public List<Task> loadTasks(String pathToFile) {

        int lineCounter = 1;
        List<Task> taskLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String buffer = reader.readLine();
            while (buffer != null) {
                taskLines.add(new Task(buffer, lineCounter));
                buffer = reader.readLine();
                lineCounter = lineCounter + 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found. " + pathToFile);
        } catch (IOException e) {
            System.out.println("File could not be read. " + pathToFile);
        }
        return taskLines;
    }

    @Override
    public void storeTasksToFile(List<Task> tasks, String pathToFile) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile))) {
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).getOriginalLine());
                if (i < tasks.size() - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<File> getTaskFiles(File folder) {
        List<File> result = new ArrayList<>();
        if (folder.isDirectory()) {
            result.addAll(Arrays.asList(folder.listFiles(new TaskFileNameFilter())));
        }
        return result;
    }

}
