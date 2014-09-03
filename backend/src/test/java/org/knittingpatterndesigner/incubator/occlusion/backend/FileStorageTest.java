package org.knittingpatterndesigner.incubator.occlusion.backend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FileStorageTest {

    private File taskTxt;

    private List<String> fileContent;
    private FileStorage backend;

    @Before
    public void setUp() throws Exception {

        backend = new FileStorage();
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
        backend.loadTasks(taskTxt.getAbsolutePath());
    }

    @Test
    public void testLoadTasks() throws Exception {

        List<Task> tasks = this.backend.loadTasks(taskTxt.getAbsolutePath());
        Assert.assertEquals("Wrong number of loaded lines",this.fileContent.size(),tasks.size());
        for (int i = 0; i < this.fileContent.size(); i++){
             Assert.assertEquals("Wrong content loaded",this.fileContent.get(i),tasks.get(i).getOriginalLine());
        }
    }
}