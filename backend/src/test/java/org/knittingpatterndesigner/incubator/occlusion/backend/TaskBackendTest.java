package org.knittingpatterndesigner.incubator.occlusion.backend;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskBackendTest {


    @InjectMocks
    private TaskBackend backend;

    @Mock
    private Storage storage;

    @Test
    public void testListEmptyFile(){

        List<Task> tasks = new ArrayList<>();
        when(storage.loadTasks("testpath")).thenReturn(tasks);
        backend.loadTasks("testpath");
        Assert.assertEquals("Wrong line count", tasks.size(), backend.getTaskLines().size());
    }
    @Test
    public void testListFile() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("@Context task1 +project"));
        tasks.add(new Task("@Context task2 +project"));
        when(storage.loadTasks("testpath")).thenReturn(tasks);

        backend.loadTasks("testpath");
        Assert.assertEquals("Wrong line count", tasks.size(), backend.getTaskLines().size());
        for (int i = 0; i < tasks.size(); i++) {
            Assert.assertTrue("Wrong content", tasks.get(i).equals(backend.getTaskLines().get(i)));
        }
    }

    @Test
    public void testGetTasksForContext() {

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("@Context task1 +project"));
        tasks.add(new Task("@Niemals task2 +project"));
        when(storage.loadTasks("testpath")).thenReturn(tasks);
        backend.loadTasks("testpath");
        String expected = tasks.get(1).getOriginalLine();
        Assert.assertEquals("Collected wrong line", expected, this.backend.getTasksForContext("Niemals").get(0).getOriginalLine());
    }

    @Test
    public void testGetTaskForProject() {

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("@Context task1 +project"));
        tasks.add(new Task("@Niemals task2 +Preis_DB"));
        when(storage.loadTasks("testpath")).thenReturn(tasks);
        backend.loadTasks("testpath");
        String expected = tasks.get(1).getOriginalLine();
        Assert.assertEquals("Collected wrong line", expected, this.backend.getTasksForProject("Preis_DB").get(0).getOriginalLine());
    }

    // @Test
    public void testAddNewTask() {

        fail();
    }
}