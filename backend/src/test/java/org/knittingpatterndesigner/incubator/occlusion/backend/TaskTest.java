package org.knittingpatterndesigner.incubator.occlusion.backend;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TaskTest {

    @Test
    public void testCanConstructTask() {

        Task task = new Task("Just a test");
        assertEquals("Wrong text stored", "Just a test", task.getOriginalLine());
    }

    @Test
    public void testMatchingContextWithPrefix(){

        Task task = new Task("@Home just a test");
        assertTrue(task.isContext("@Home"));
    }

    @Test
    public void testMatchingContextWithoutPrefix(){

        Task task = new Task("@Home just a test");
        assertTrue(task.isContext("Home"));
    }

    @Test
    public void testNonMatchingContextWithPrefix(){
        Task task = new Task("@Home Work is just a test");
        assertFalse(task.isContext("@Work"));
    }

    @Test
    public void testNonMatchingContextWithoutPrefix(){
        Task task = new Task("@Home Work is just a test");
        assertFalse(task.isContext("Work"));
    }

    @Test
    public void testMatchingProjectWithPrefix(){
        Task task = new Task("@Home this is just another project +project.");
        assertTrue(task.isProject("+project"));
    }

    @Test
    public void testMatchingProjectWithoutPrefix(){
        Task task = new Task("@Home this is just another project +project.");
        assertTrue(task.isProject("project"));
    }

    @Test
    public void testNonMatchingProjectWithPrefix(){
        Task task = new Task("@Home this is just another project +another_project.");
        assertFalse(task.isProject("+project"));
    }
    @Test
    public void testNonMatchingProjectWithoutPrefix(){
        Task task = new Task("@Home this is just another project +another_project.");
        assertFalse(task.isProject("project"));
    }
}