# occlusion


This is a todo.txt compatible task-shell, providing you with rudimentary functions, by now.

## Build

To build the software, after being checked out, you need following prerequisites:
* Java 8 JDK
* Maven 3 in a recent version

Before you can build the project you need to add the command line library cliche to your local maven repository. If you
have a Mac or Linux System you will find a proper script in the directory build-helpers.

On Windows you will have to do these steps manually.

Then you can build the software with mvn clean install -Ppackage
This call will give you a executable fat-jar under frontend/target.

## Preparation

To run the software you need a folder containing three textfiles named todo.txt, done.txt and contexts.txt.
They will store your data. You are currently not able to add new contexts using the programm, so you have to make these 
entries on your own. 
One context per line, with a leading @-sign in front and no spaces, for Example: @Home .

## Run

Then you will be able to start the programm with:

java -jar frontend/target/occlusion-frontend-1.0-SNAPSHOT-jar-with-dependencies.jar path/to/taskfolder

With ?list you get a list of all possible commands.