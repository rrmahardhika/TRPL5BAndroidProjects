package com.mahardhikaapp.trpl5bandroidprojects

import org.junit.Test
import org.junit.Assert.*

class RegistrationExampleTest {
    @Test
    fun `valid username and correctly repeated password returns true`() {
        val result = RegistrationExample.validateRegistrationInput(
            "Peter",
            "123",
            "123"
        )
        assertEquals(result,true)
    }
}