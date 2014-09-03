package org.knittingpatterndesigner.incubator.occlusion.backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 03.09.14
 */
public class FileStorage implements Storage {

    private String pathToTaskFile;
    @Override
    public List<Task> loadTasks(String pathToTaskFile) {

        this.pathToTaskFile = pathToTaskFile;
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

    @Override
    public void storeTasks(List<Task> tasks) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(pathToTaskFile))){
            for (int i = 0; i < tasks.size(); i++){
                writer.write(tasks.get(i).getOriginalLine());
                if (i < tasks.size()-1){
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
