package de.marcphilipp.gradle.example;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class GreetingTask extends DefaultTask {
    private final String greeting;

    public GreetingTask(String greeting) {
        this.greeting = greeting;
    }

    @TaskAction
    public void greet() {
        System.out.println("foo: " + greeting);
    }
}
