package org.knittingpatterndesigner.incubator.occlusion.backend;

/**
 * This class represents a task.
 */
public class Task {

    /**
     * This is the line as read from the file.
     */
    private String originalLine;
    private int linenumber;

    /**
     * Initalize the class and setting the given string as original line.
     *
     * @param taskString The line as read from the file.
     */
    public Task(String taskString) {
        this.originalLine = taskString;
        this.linenumber = Integer.MAX_VALUE;
    }

    /**
     * Initalize the class and setting the given string as original line.
     *
     * @param taskString The line as read from the file.
     * @param linenumber The number of the line in the file.
     */
    public Task(String taskString, int linenumber) {
        this.originalLine = taskString;
        this.linenumber = linenumber;
    }

    /**
     * Returns the number of the line in the file
     *
     * @return number of the line
     */
    public int getLinenumber() {
        return linenumber;
    }

    /**
     * This returns the original line stored.
     *
     * @return the original line stored
     */
    public String getOriginalLine() {
        return originalLine;
    }

    /**
     * This returns, if the current tasks has the given context.
     *
     * @param context The context for which the task is checked
     * @return true when it matches the project
     */
    public boolean isContext(String context) {
        if (context.startsWith("@")) {
            return originalLine.contains(context);
        } else {
            return originalLine.contains("@" + context);
        }
    }

    /**
     * This returns, if this task belongs to the given Project.
     *
     * @param project The project for which the task is checked.
     * @return true when it matches the project
     */
    public boolean isProject(String project) {
        if (project.startsWith("+")) {
            return originalLine.contains(project);
        } else {
            return originalLine.contains("+" + project);
        }
    }

    @Override
    public String toString() {
        return getOriginalLine();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;

        if (!originalLine.equals(task.originalLine)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return originalLine.hashCode();
    }

    public String getContext() {
        return originalLine.substring(originalLine.indexOf('@'), originalLine.indexOf(' ', originalLine.indexOf('@')));
    }

    public void setLinenumber(int linenumber) {
        this.linenumber = linenumber;
    }
}
