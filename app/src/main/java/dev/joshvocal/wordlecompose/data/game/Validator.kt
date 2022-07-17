package dev.joshvocal.wordlecompose.data.game


object Validator {
    fun isWordValid(word: String, dictionary: List<String>): InputState {
        return when {
            word.length < WordleGame.MAX_WORD_LENGTH -> InputState.NotEnoughLetters
            word.length == WordleGame.MAX_WORD_LENGTH && !dictionary.contains(word.lowercase()) -> InputState.NotInWordList
            else -> InputState.Valid
        }
    }

    fun validateGuess(guess: String, target: String): WordGuess {
        val validatedGuess = Array(target.length) {
            CharacterState.Unknown
        }

        val unconfirmedChars = hashSetOf<Char>()

        val guessChars = guess.uppercase().toCharArray()
        val targetChars = target.uppercase().toCharArray()
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
                CharacterState.Present
            } else {
                CharacterState.Absent
            }
        }

        return guessChars.zip(validatedGuess).map { (character, state) ->
            WordleLetter(character = character, state = state)
        }.toTypedArray()
    }
}
