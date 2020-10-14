package org.example;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClassTest {

    @Test(dataProvider = "test1", dataProviderClass = StaticProvider.class)
    public void verifyData1(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }

    public static class StaticProvider {

        @DataProvider(name = "test1")
        public static Object[][] createData1() {
            return new Object[][]{
                    {"Cedric", 36},
                    {"Anne", 37},
            };
        }
    }
}
