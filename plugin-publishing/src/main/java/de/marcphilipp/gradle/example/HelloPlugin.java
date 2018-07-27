package de.marcphilipp.gradle.example;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class HelloPlugin implements Plugin<Project> {
    @Override
    public void apply(Project target) {
        target.getTasks().register("hello", GreetingTask.class, "Hello");
    }
}
