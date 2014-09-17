package org.knittingpatterndesigner.incubator.occlusion.backend;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskBackendTest {


    @InjectMocks
    private TaskBackend backend;

    @Mock
    private Storage storage;

    @Test
    public void testListEmptyFile() {

        List<Task> tasks = new ArrayList<>();
        when(storage.loadTasks("folder/todo.txt")).thenReturn(tasks);
        backend.setTaskFolder("folder");
        backend.loadTasks();
        Assert.assertEquals("Wrong line count", tasks.size(), backend.getTaskLines().size());
    }

    @Test
    public void testListFile() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("@Context task1 +project"));
        tasks.add(new Task("@Context task2 +project"));
        backend.setTaskFolder("folder");
        when(storage.loadTasks("folder/todo.txt")).thenReturn(tasks);

        backend.loadTasks();
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
        when(storage.loadTasks("folder/todo.txt")).thenReturn(tasks);
        backend.setTaskFolder("folder");
        backend.loadTasks();
        String expected = tasks.get(1).getOriginalLine();
        Assert.assertEquals("Collected wrong line", expected, this.backend.getTasksForContext("Niemals").get(0).getOriginalLine());
    }

    @Test
    public void testGetTaskForProject() {

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("@Context task1 +project"));
        tasks.add(new Task("@Niemals task2 +Preis_DB"));
        when(storage.loadTasks("folder/todo.txt")).thenReturn(tasks);
        backend.setTaskFolder("folder");
        backend.loadTasks();
        String expected = tasks.get(1).getOriginalLine();
        Assert.assertEquals("Collected wrong line", expected, this.backend.getTasksForProject("Preis_DB").get(0).getOriginalLine());
    }

    @Test
    public void testAddNewTask() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("@Context task1 +project"));
        tasks.add(new Task("@Niemals task2 +Preis_DB"));
        backend.setTaskFolder("folder");
        String pathToTaskFile = "folder/todo.txt";
        when(storage.loadTasks(pathToTaskFile)).thenReturn(tasks);
        backend.loadTasks();
        backend.addTask(new Task("@Neu task +projectNew"));
        verify(storage).storeTasksToFile(Matchers.anyListOf(Task.class), eq(pathToTaskFile));
        List<Task> retrievedTasks = backend.getTaskLines();
        tasks.add(new Task("@Neu task +projectNew"));
        compareListsForEquality(tasks, retrievedTasks);
    }

    private void compareListsForEquality(List<Task> expectedTasks, List<Task> retrievedTasks) {
        Assert.assertEquals("Not all retrieved", expectedTasks.size(), retrievedTasks.size());
        for (int i = 0; i < retrievedTasks.size(); i++) {
            Assert.assertEquals(expectedTasks.get(i), retrievedTasks.get(i));
        }
    }

    @Test
    public void testListContexts() {
        List<Task> contexts = new ArrayList<>();
        contexts.add(new Task("@Context1 blubb"));
        contexts.add(new Task("@Context2 bla"));
        backend.setTaskFolder("folder");
        when(storage.loadTasks("folder/contexts.txt")).thenReturn(contexts);
        backend.loadTasks();
        List<Task> receivedList = backend.getContexts();
        compareListsForEquality(contexts, receivedList);
    }
}