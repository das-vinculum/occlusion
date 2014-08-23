package org.knittingpatterndesigner.incubator.occlusion.shell;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;

public class OcclusionShellTest {

    private File taskTxt;

    private String fileContent;

    @Before
    public void setUp() throws Exception {

        URL url = getClass().getClassLoader().getResource("org/knittingpatterndesigner/incubator/occlusion/todo.txt");
        this.taskTxt = new File(url.getFile());
        FileReader reader = new FileReader(this.taskTxt);
        BufferedReader bufferedReader = new BufferedReader(reader);
        this.fileContent = bufferedReader.readLine();
        bufferedReader.close();
        reader.close();
    }

    @Test
    public void testListFile() {

        OcclusionShell shell = new OcclusionShell(taskTxt.getAbsolutePath());


        Assert.assertEquals("Wrong content", this.fileContent, shell.listTasks());
    }
}