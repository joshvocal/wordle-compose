package dev.joshvocal.wordlecompose.data.game

enum class CharacterState {
    Correct,
    WrongPosition,
    Incorrect,
    Unknown,
}

typealias WordGuess = Array<CharacterState>

fun WordGuess.isCorrect(): Boolean = all { characterState ->
    characterState == CharacterState.Correct
}

object Validator {
    fun validate(guess: String, target: String): WordGuess {
        val validatedGuess = Array(target.length) {
            CharacterState.Unknown
        }

        val unconfirmedChars = hashSetOf<Char>()

        val guessChars = guess.lowercase().toCharArray()
        val targetChars = target.lowercase().toCharArray()
        val guessAndTargetChars = guessChars.zip(targetChars)

        guessAndTargetChars.forEachIndexed { index, (guessChar, targetChar) ->
            validatedGuess[index] = if (guessChar == targetChar) {
                CharacterState.Correct
            } else {
                unconfirmedChars.add(targetChar)
                CharacterState.Unknown
            }
        }

        guessChars.forEachIndexed { index, guessChar ->
            if (validatedGuess[index] != CharacterState.Unknown) {
                return@forEachIndexed
            }

            validatedGuess[index] = if (unconfirmedChars.contains(guessChar)) {
                unconfirmedChars.remove(guessChar)
                CharacterState.WrongPosition
            } else {
                CharacterState.Incorrect
            }
        }

        return validatedGuess
    }
}