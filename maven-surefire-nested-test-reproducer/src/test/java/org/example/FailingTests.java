package org.example;

public class FailingTests extends AbstractTests {
    @Override
    boolean shouldSucceed() {
        return false;
    }
}
