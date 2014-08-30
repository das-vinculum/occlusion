package org.knittingpatterndesigner.incubator.occlusion.backend;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 26.08.14
 */
public class Task {

    private String context;

    private String originalLine;

    public Task(String taskString) {
        this.originalLine = taskString;

    }

    public String getOriginalLine() {
        return originalLine;
    }

    public boolean isContext(String s) {
        if (s.startsWith("@")) {
            return originalLine.contains(s);
        } else {
            return originalLine.contains("@" + s);
        }
    }

    @Override
    public String toString() {
        return getOriginalLine();
    }

    public boolean isProject(String searchString) {
        if (searchString.startsWith("+")) {

            return originalLine.contains(searchString);
        } else {

            return originalLine.contains("+" + searchString);
        }
    }
}
