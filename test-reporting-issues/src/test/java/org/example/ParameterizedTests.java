package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedTests {

    @ParameterizedTest
    @DisplayName("1st test")
    @ValueSource(strings = {"foo", "bar"})
    void test1(String param) {
        assertEquals("foo", param);
    }

    @ParameterizedTest
    @DisplayName("2nd test")
    @ValueSource(strings = {"foo", "bar"})
    void test2(String param) {
        assertEquals("foo", param);
    }
}
