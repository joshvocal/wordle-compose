package dev.joshvocal.wordlecompose.data.game

import org.junit.Assert.assertEquals
import org.junit.Test

class WordGuessTest {

    @Test
    fun `when all guess letters are correct, expect a correct guess`() {
        val guess = "hello"
        val target = "hello"

        val expect = true

        val actual = Validator.validate(
            guess = guess,
            target = target,
        ).isCorrect()

        assertEquals(expect, actual)
    }

    @Test
    fun `when all guess letters are not correct, expect a wrong guess`() {
        val guess = "hello"
        val target = "world"

        val expect = false

        val actual = Validator.validate(
            guess = guess,
            target = target,
        ).isCorrect()

        assertEquals(expect, actual)
    }
}