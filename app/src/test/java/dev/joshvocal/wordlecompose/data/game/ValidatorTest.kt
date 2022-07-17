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

        val actual = Validator.validateGuess(
            guess = guess,
            target = target,
        ).getStates()

        assertArrayEquals(expect, actual)
    }

    @Test
    fun `when a guess letter is absent, expect an incorrect absent`() {
        val guess = "world"
        val target = "morld"

        val expect = arrayOf(
            CharacterState.Absent,
            CharacterState.Correct,
            CharacterState.Correct,
            CharacterState.Correct,
            CharacterState.Correct,
        )

        val actual = Validator.validateGuess(
            guess = guess,
            target = target,
        ).getStates()

        assertArrayEquals(expect, actual)
    }

    @Test
    fun `when there are guess letters that are in the wrong position, expect those letter to be present`() {
        val guess = "money"
        val target = "nomey"

        val expect = arrayOf(
            CharacterState.Present,
            CharacterState.Correct,
            CharacterState.Present,
            CharacterState.Correct,
            CharacterState.Correct,
        )

        val actual = Validator.validateGuess(
            guess = guess,
            target = target,
        ).getStates()

        assertArrayEquals(expect, actual)
    }

    @Test
    fun `when there are multiple duplicate correct characters, expect only the first duplicated character to be correct`() {
        val guess = "carry"
        val target = "route"

        val expect = arrayOf(
            CharacterState.Absent,
            CharacterState.Absent,
            CharacterState.Present,
            CharacterState.Absent,
            CharacterState.Absent,
        )

        val actual = Validator.validateGuess(
            guess = guess,
            target = target,
        ).getStates()

        assertArrayEquals(expect, actual)
    }

    @Test
    fun `when there are multiple duplicate correct characters, expect only the first duplicated character to be correct 2`() {
        val guess = "woods"
        val target = "knots"

        val expect = arrayOf(
            CharacterState.Absent,
            CharacterState.Absent,
            CharacterState.Correct,
            CharacterState.Absent,
            CharacterState.Correct,
        )

        val actual = Validator.validateGuess(
            guess = guess,
            target = target,
        ).getStates()

        assertArrayEquals(expect, actual)
    }
}
