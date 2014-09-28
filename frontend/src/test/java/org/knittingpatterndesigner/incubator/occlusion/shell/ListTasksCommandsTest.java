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

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListTasksCommandsTest {

    private ListTasksCommands shell;

    @Mock
    private Backend backend;

    @Before
    public void setUp() {
        shell = new ListTasksCommands(backend);
    }

    @Test
    public void testListTasksWithEmptyList() {
        List<Task> taskList = new ArrayList<>();
        when(backend.getTaskLines()).thenReturn(taskList);
    }

    @Test
    public void testListTasks() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("task1"));
        when(backend.getTaskLines()).thenReturn(taskList);
        Assert.assertEquals("listTasks did not return proper line", prepareListForCheck(taskList), shell.listTasks());
    }

    @Test
    public void testListTasksByContext() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("@Context task1"));
        taskList.add(new Task("@Context task2"));
        when(backend.getTasksForContext("Context")).thenReturn(taskList);
        Assert.assertEquals("listTasks did not return proper line", prepareListForCheck(taskList), shell.listTasksByContext("Context"));
    }

    @Test
    public void testListTasksByProject() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("@Context task1 +project"));
        taskList.add(new Task("@Context task2 +project"));
        when(backend.getTasksForProject("project")).thenReturn(taskList);
        Assert.assertEquals("listTasks did not return proper line", prepareListForCheck(taskList), shell.listTasksByProject("project"));
    }

    @Test
    public void addTask() {
        Task expectedTask = new Task("Das ist ein neuer Task für @Hause aus dem +Projekt");
        shell.add("Das", "ist", "ein", "neuer", "Task", "für", "@Hause", "aus", "dem", "+Projekt");
        verify(backend).addTask(expectedTask);
    }

    @Test
    public void testListContexts() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("@Context1"));
        taskList.add(new Task("@Context2"));
        when(backend.getContexts()).thenReturn(taskList);
        Assert.assertEquals("Returned contexts were not correct.", prepareListForCheck(taskList)
                , shell.listContexts());
    }

    @Test
    public void testMarkTaskAsDone(){
       List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("@Context task1"));
        taskList.add(new Task("@Context task3"));
        assertThat(shell.markTaskAsDone(2)).isEqualTo("Task 2 has been marked as done.");
        verify(backend).markTaskAsDone(2);
    }

    private String prepareListForCheck(List<Task> tasks) {
        StringBuilder builder = new StringBuilder();
        for (Task task : tasks) {
            builder.append(task.getLinenumber()).append(':').append(' ');
            builder.append(task.getOriginalLine());
            builder.append("\n");
        }
        return builder.toString();
    }
}