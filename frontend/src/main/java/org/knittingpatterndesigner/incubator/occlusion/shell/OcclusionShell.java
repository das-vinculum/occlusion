package org.knittingpatterndesigner.incubator.occlusion.shell;

import asg.cliche.Command;
import asg.cliche.ShellFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Main starting point for the shell interface
 */
public class OcclusionShell {

    private File taskFile;
    private List<String> taskLines;

    public OcclusionShell(String absolutePath) {

        this.taskFile = new File(absolutePath);

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

    @Command
    public String helloWorld() {

        return "Hello World!";
    }

    @Command
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) throws IOException {

        ShellFactory.createConsoleShell("hello", "", new OcclusionShell(args[0])).commandLoop();
    }

    @Command
    public String listTasks() {

        StringBuffer result = new StringBuffer();
        for (String line : this.taskLines) {

            result.append(line);
        }
        return result.toString();
    }
}
