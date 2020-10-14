package org.example;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

public class DataProviderClassTest {

    @ParameterizedTest
    @ArgumentsSource(StaticProvider.class)
    public void verifyData1(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }

    public static class StaticProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("Cedric", 36),
                    Arguments.of("Anne", 37)
            );
        }
    }
}
