package org.knittingpatterndesigner.incubator.occlusion.backend;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created with IntelliJ IDEA.
 * Created by: florianzeidler
 * Created: 14.09.14
 */
class TaskFileNameFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return name.matches(".*\\.txt");
    }
}
