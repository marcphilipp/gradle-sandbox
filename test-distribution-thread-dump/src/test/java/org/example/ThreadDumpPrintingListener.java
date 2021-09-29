package org.example;

import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.LauncherSessionListener;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.time.Instant;

public class ThreadDumpPrintingListener implements LauncherSessionListener {

    @Override
    public void launcherSessionClosed(LauncherSession session) {
        System.out.println("ThreadDumpPrintingListener.launcherSessionClosed");
        printThreadDump();
    }

    private void printThreadDump() {
        var stringWriter = new StringWriter();
        try (var out = new PrintWriter(stringWriter)) {
            out.println("------------------------------------------------------>");
            out.printf("Thread dump of current process at %s%n%n", Instant.now());
            for (ThreadInfo threadInfo : ManagementFactory.getThreadMXBean().dumpAllThreads(true, true)) {
                out.print(threadInfo);
            }
            out.print("<------------------------------------------------------");
        }
        System.out.println(stringWriter);
    }
}
