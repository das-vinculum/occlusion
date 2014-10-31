package org.knittingpatterndesigner.incubator.occlusion.backend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

public class TaskFileNameFilterTest {

    private TaskFileNameFilter fileNameFilter;

    private File folder;

    @Before
    public void setUp() {
        this.fileNameFilter = new TaskFileNameFilter();
        URL url = getClass().getClassLoader().getResource("org/knittingpatterndesigner/incubator/occlusion/fetchTaskFiles");
        folder = new File(url.getFile());

    }

    @Test
    public void testAcceptableFiles() {

        Assert.assertTrue(this.fileNameFilter.accept(folder, "contexts.txt"));
        Assert.assertTrue(this.fileNameFilter.accept(folder, "projects.txt"));
        Assert.assertTrue(this.fileNameFilter.accept(folder, "todo.txt"));
        Assert.assertTrue(this.fileNameFilter.accept(folder, "maybe.txt"));
        Assert.assertTrue(this.fileNameFilter.accept(folder, "done.txt"));

    }

    @Test
    public void testUnacceptableFiles() {

        Assert.assertFalse(this.fileNameFilter.accept(folder, "blubb.bla"));
        Assert.assertFalse(this.fileNameFilter.accept(folder, "faketxt"));
    }

}