package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import module org.junit.aggregator;

class ExampleTests {

	@Test
	void someTest() {
		assertEquals("org.example", getClass().getModule().getName());
		fail("boom");
	}

	void main() {
		JUnit.run(this);
	}
}
