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

public class FileBackendTest {

    private File taskTxt;

    private List<String> fileContent;
    private Backend backend;

    @Before
    public void setUp() throws Exception {

        backend = new FileBackend();
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
        backend.loadTaskFile(taskTxt.getAbsolutePath());
    }

    @Test
    public void testListFile() {

        Assert.assertEquals("Wrong line count", this.fileContent.size(), backend.getTaskLines().size());
        for (int i = 0; i < this.fileContent.size(); i++) {
            Assert.assertEquals("Wrong content", this.fileContent.get(i), backend.getTaskLines().get(i).getOriginalLine());

        }
    }

    @Test
    public void testGetTasksForContext() {
        String expected = this.fileContent.get(1);
        Assert.assertEquals("Collected wrong line", expected, this.backend.getTasksForContext("Niemals").get(0).getOriginalLine());

    }
}