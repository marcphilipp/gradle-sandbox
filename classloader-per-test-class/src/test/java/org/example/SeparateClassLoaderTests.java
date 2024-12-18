package org.example;

import org.junit.jupiter.api.Test;

import java.net.URL;

@SeparateClassLoader(factory = CustomClassLoaderFactory.class)
public class SeparateClassLoaderTests {

    @Classpath
    static URL[] classpath() {
        return new URL[]{
                SeparateClassLoaderTests.class.getProtectionDomain().getCodeSource().getLocation()
        };
    }

    @Test
    void test() {
        var classLoader = Thread.currentThread().getContextClassLoader();
        System.out.printf("%s class loader: %s (%s)%n", getClass().getName(), classLoader, classLoader.getName());
    }
}
