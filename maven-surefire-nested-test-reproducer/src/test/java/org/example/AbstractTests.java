package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class AbstractTests {
    abstract boolean shouldSucceed();
    @Nested
    class Inner {
        @Test
        void test() {
            assertTrue(shouldSucceed());
        }
    }
}
