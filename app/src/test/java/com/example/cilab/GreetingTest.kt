package com.example.cilab

import org.junit.Assert.assertEquals
import org.junit.Test

class GreetingTest {

    @Test
    fun createGreeting_returnsCorrectMessage() {
        val result = createGreeting("Zayd")

        assertEquals("Hello, Zayd!", result)
    }
}

//The test repo is https://github.com/zaydaly05/Week6Task9