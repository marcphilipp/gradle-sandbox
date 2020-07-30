package org.example;

import org.gradle.api.GradleException;
import org.gradle.api.JavaVersion;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class GradleTestkitCrossJavaVersionPlugin implements Plugin<Project> {
    @Override
    public void apply(Project target) {
        target.getTasks().register("throwException", task -> task.doFirst(__ -> {
            throw new GradleException(JavaVersion.current().toString());
        }));
    }
}
