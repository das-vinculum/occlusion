package org.knittingpatterndesigner.incubator.occlusion.backend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileStorageTest {

    private File taskTxt;

    private List<String> fileContent;

    private FileStorage fileStorage;

    @Before
    public void setUp() throws Exception {
        fileStorage = new FileStorage();
        this.fileContent = new ArrayList<>();
        URL url = getClass().getClassLoader().getResource("org/knittingpatterndesigner/incubator/occlusion/todo.txt");
        this.taskTxt = new File(url.getFile());
        FileReader reader = new FileReader(this.taskTxt);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String buffer = bufferedReader.readLine();
        while (buffer != null) {
            this.fileContent.add(buffer);
            buffer = bufferedReader.readLine();
        }
        bufferedReader.close();
        reader.close();
        fileStorage.loadTasks(taskTxt.getAbsolutePath());
    }

    @Test
    public void testListTodoFiles() {
        URL url = getClass().getClassLoader().getResource("org/knittingpatterndesigner/incubator/occlusion/fetchTaskFiles");
        File folder = new File(url.getFile());
        List<File> taskFiles = this.fileStorage.getTaskFiles(folder);
        List<File> founded = Arrays.asList(folder.listFiles(new TaskFileNameFilter()));
        Assert.assertEquals("Fetched too much files.", founded.size(), taskFiles.size());
        for (File taskFile : founded) {
            if (taskFile.getName().matches(".*\\.txt")) {
                Assert.assertTrue(taskFiles.contains(taskFile));
            }
        }
    }

    @Test
    public void testLoadEmptyFile() {
        URL url = getClass().getClassLoader().getResource("org/knittingpatterndesigner/incubator/occlusion/empty.txt");
        File workfile = new File(url.getFile());
        List<Task> tasks = this.fileStorage.loadTasks(workfile.getAbsolutePath());
        Assert.assertNotNull(tasks);
    }

    @Test
    public void testLoadTasks() throws Exception {
        List<Task> tasks = this.fileStorage.loadTasks(taskTxt.getAbsolutePath());
        Assert.assertEquals("Wrong number of loaded lines", this.fileContent.size(), tasks.size());
        for (int i = 0; i < this.fileContent.size(); i++) {
            Assert.assertEquals("Wrong content loaded", this.fileContent.get(i), tasks.get(i).getOriginalLine());
        }
    }

    @Test
    public void testStoreTasks() throws IOException {
        URL url = getClass().getClassLoader().getResource("org/knittingpatterndesigner/incubator/occlusion/tmp.txt");
        File workfile = new File(url.getFile());
        Files.copy(this.taskTxt.toPath(), workfile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        List<Task> tasks = this.fileStorage.loadTasks(workfile.getAbsolutePath());
        tasks.add(new Task("Another task"));

        this.fileStorage.storeTasksToFile(tasks,url.getFile());
        List<Task> reloadedTasks = this.fileStorage.loadTasks(workfile.getAbsolutePath());
        Assert.assertEquals("Tasks was not stored", tasks.size(), reloadedTasks.size());

        for (int i = 0; i < tasks.size(); i++) {
            Assert.assertEquals("Wrong content loaded", tasks.get(i), reloadedTasks.get(i));
        }

    }
}