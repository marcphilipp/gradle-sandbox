package de.marcphilipp.gradle.example;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class GoodbyePlugin implements Plugin<Project> {
    @Override
    public void apply(Project target) {
        target.getTasks().register("goodbye", GreetingTask.class, "Goodbye");
    }
}
