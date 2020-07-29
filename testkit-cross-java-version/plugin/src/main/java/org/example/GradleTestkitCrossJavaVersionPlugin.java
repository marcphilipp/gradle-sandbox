package org.example;

import org.gradle.api.JavaVersion;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class GradleTestkitCrossJavaVersionPlugin implements Plugin<Project> {
    @Override
    public void apply(Project target) {
        target.getTasks().register("printJavaVersion", task -> task.doFirst(__ -> System.out.println(JavaVersion.current())));
    }
}
