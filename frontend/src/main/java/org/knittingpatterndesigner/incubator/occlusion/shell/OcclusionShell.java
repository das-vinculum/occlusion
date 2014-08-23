package org.knittingpatterndesigner.incubator.occlusion.shell;

import asg.cliche.Command;
import asg.cliche.ShellFactory;

import java.io.IOException;

/**
 * Main starting point for the shell interface
 */
public class OcclusionShell {

    @Command
    public String helloWorld(){

        return "Hello World!";
    }

    @Command
    public int add(int a, int b){
        return a+b;
    }

    public static void main(String[] args) throws IOException {

        ShellFactory.createConsoleShell("hello", "",new OcclusionShell()).commandLoop();
    }
}
