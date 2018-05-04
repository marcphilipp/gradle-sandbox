package org.gradle.sample;

import org.gradle.api.internal.AbstractTask;
import org.gradle.api.tasks.TaskAction;

public class HelloWorldTask extends AbstractTask {

    @TaskAction
    public void sayHello() {
        System.out.println("Hello World");
    }

}
