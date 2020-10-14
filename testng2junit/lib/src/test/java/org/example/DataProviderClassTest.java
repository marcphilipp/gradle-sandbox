package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class DataProviderClassTest {

    @ParameterizedTest
    @MethodSource("org.example.DataProviderClassTest$StaticProvider#createData1")
    public void verifyData1(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }

    public static class StaticProvider {

        public static Object[][] createData1() {
            return new Object[][]{
                    {"Cedric", 36},
                    {"Anne", 37},
            };
        }
    }
}
