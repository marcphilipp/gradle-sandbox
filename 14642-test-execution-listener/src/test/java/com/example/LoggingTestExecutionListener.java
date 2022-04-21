package com.example;

import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

public class LoggingTestExecutionListener implements TestExecutionListener {

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        System.out.println("Test plan started");
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        System.out.println("Test plan finished");
    }
}
