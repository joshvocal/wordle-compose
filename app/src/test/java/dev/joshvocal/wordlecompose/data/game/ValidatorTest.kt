package dev.joshvocal.wordlecompose.data.game

import org.junit.Assert.assertArrayEquals
import org.junit.Test

class ValidatorTest {

    @Test
    fun `when all guess letters are correct, expect all characters are correct`() {
        val guess = "hello"
        val target = "hello"

        val expect = arrayOf(
            CharacterState.Correct,
            CharacterState.Correct,
            CharacterState.Correct,
            CharacterState.Correct,
            CharacterState.Correct,
        )

        val actual = Validator.validate(
            guess = guess,
            target = target,
        )

        assertArrayEquals(expect, actual)
    }

    @Test
    fun `when a guess letter is incorrect, expect an incorrect letter`() {
        val guess = "world"
        val target = "morld"

        val expect = arrayOf(
            CharacterState.Incorrect,
            CharacterState.Correct,
            CharacterState.Correct,
            CharacterState.Correct,
            CharacterState.Correct,
        )

        val actual = Validator.validate(
            guess = guess,
            target = target,
        )

        assertArrayEquals(expect, actual)
    }

    @Test
    fun `when there are guess letters in the wrong position, expect those letter in the wrong position`() {
        val guess = "money"
        val target = "nomey"

        val expect = arrayOf(
            CharacterState.WrongPosition,
            CharacterState.Correct,
            CharacterState.WrongPosition,
            CharacterState.Correct,
            CharacterState.Correct,
        )

        val actual = Validator.validate(
            guess = guess,
            target = target,
        )

        assertArrayEquals(expect, actual)
    }

    @Test
    fun `when there are multiple duplicate correct characters, expect only the first duplicated character to be correct`() {
        val guess = "carry"
        val target = "route"

        val expect = arrayOf(
            CharacterState.Incorrect,
            CharacterState.Incorrect,
            CharacterState.WrongPosition,
            CharacterState.Incorrect,
            CharacterState.Incorrect,
        )

        val actual = Validator.validate(
            guess = guess,
            target = target,
        )

        assertArrayEquals(expect, actual)
    }

    @Test
    fun `when there are multiple duplicate correct characters, expect only the first duplicated character to be correct 2`() {
        val guess = "woods"
        val target = "knots"

        val expect = arrayOf(
            CharacterState.Incorrect,
            CharacterState.Incorrect,
            CharacterState.Correct,
            CharacterState.Incorrect,
            CharacterState.Correct,
        )

        val actual = Validator.validate(
            guess = guess,
            target = target,
        )

        assertArrayEquals(expect, actual)
    }
}