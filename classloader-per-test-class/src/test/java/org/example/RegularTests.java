package org.example;

import org.junit.jupiter.api.Test;

public class RegularTests {

    @Test
    void test() {
        var classLoader = Thread.currentThread().getContextClassLoader();
        System.out.printf("%s class loader: %s (%s)%n", getClass().getName(), classLoader, classLoader.getName());
    }
}
