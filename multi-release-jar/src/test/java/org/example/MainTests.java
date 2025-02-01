package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTests {

    @Test
    @EnabledForJreRange(max = JRE.JAVA_14)
    void testBeforeJava15() {
        assertFalse(Main.someMainMethod(), "Java 8 compatible version of Alg is called");
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_15)
    void testOnJava15OrLater() {
        assertTrue(Main.someMainMethod(), "Java 15 compatible version of Alg is called");
    }
}
