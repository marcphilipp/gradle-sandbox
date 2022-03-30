package com.baeldung.mockk

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InjectTestService {
    lateinit var service1: TestableService

    fun invokeService1(): String {
        return service1.getDataFromDb("Test Param")
    }
}

class AnnotationMockKUnitTest {

    @MockK
    lateinit var service1: TestableService

    @InjectMockKs
    var objectUnderTest = InjectTestService()

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun givenServiceMock_whenCallingMockedMethod_thenCorrectlyVerified() {
        // given
        every { service1.getDataFromDb("Test Param") } returns "No"
        // when
        val result = objectUnderTest.invokeService1()
        // then
        assertEquals("No", result)
    }

}
