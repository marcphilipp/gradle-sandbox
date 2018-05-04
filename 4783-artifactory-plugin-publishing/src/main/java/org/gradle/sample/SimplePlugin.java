package org.gradle.sample;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class SimplePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getTasks().create("hello", HelloWorldTask.class);
    }
}
