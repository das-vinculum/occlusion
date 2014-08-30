package org.knittingpatterndesigner.incubator.occlusion.shell;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.knittingpatterndesigner.incubator.occlusion.backend.Backend;
import org.knittingpatterndesigner.incubator.occlusion.backend.Task;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListTaskCommandsTest {

    private ListTasksCommands shell;

    @Mock
    private Backend backend;

    @Before
    public void setUp() {
        shell = new ListTasksCommands(backend);
    }

    @Test
    public void testListTasks() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("task1"));
        when(backend.getTaskLines()).thenReturn(taskList);
        Assert.assertEquals("listTasks did not return proper line", "task1\n", shell.listTasks());
    }

    @Test
    public void testListTasksByContext() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("@Context task1"));
        taskList.add(new Task("@Context task2"));
        when(backend.getTasksForContext("Context")).thenReturn(taskList);
        String expected = "@Context task1\n@Context task2\n";
        Assert.assertEquals("listTasks did not return proper line", expected, shell.listTasksByContext("Context"));
    }

    @Test
    public void testListTasksByProject(){
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("@Context task1 +project"));
        taskList.add(new Task("@Context task2 +project"));
        when(backend.getTasksForProject("project")).thenReturn(taskList);
        String expected = "@Context task1 +project\n@Context task2 +project\n";
        Assert.assertEquals("listTasks did not return proper line", expected, shell.listTasksByProject("project"));
    }
}