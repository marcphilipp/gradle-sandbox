package org.example;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicTests {

    @TestFactory
    Stream<DynamicNode> testFactory() {
        return Stream.of(dynamicContainer("some container", Stream.of(
                dynamicContainer("some nested container", Stream.of(
                        dynamicTest("foo", () -> {
                            assertTrue(true);
                        }),
                        dynamicTest("bar", () -> {
                            assertTrue(false);
                        })
                ))
        )));
    }

    @TestFactory
    Stream<DynamicNode> anotherTestFactory() {
        return Stream.of(dynamicTest("foo", () -> {
            assertTrue(true);
        }));
    }
}
