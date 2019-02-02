package org.junit.example;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Collections.singletonList;

abstract class BaseTest {
    @RepeatedTest(1_000)
    void test(@TempDir Path tempDir) throws Exception {
        Files.write(tempDir.resolve("foo.txt"), singletonList("bar"));
    }
}
