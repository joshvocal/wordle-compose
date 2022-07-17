package dev.joshvocal.wordlecompose.data.game

import java.lang.Integer.max


data class WordleGame(
    val targetWord: String,
    val dictionary: List<String>,
    val guesses: List<WordGuess> = listOf(),
    val userInput: String = "",
) {

    val state: WordleGameState = when {
        isGuessCorrect() -> WordleGameState.Won
        guesses.size == MAX_NUMBER_OF_GUESSES -> WordleGameState.Lost
        else -> WordleGameState.Playing
    }

    val inputState = Validator.isWordValid(word = userInput, dictionary = dictionary)

    val victoryMessage = VICTORY_MESSAGES[max(guesses.size - 1, 0)]

    private fun isGuessCorrect(): Boolean = guesses.any { guess ->
        guess.isCorrect()
    }

    companion object Constants {
        const val MAX_NUMBER_OF_GUESSES = 6
        const val MAX_WORD_LENGTH = 5

        val VICTORY_MESSAGES = arrayOf(
            "Genius",
            "Magnificent",
            "Impressive",
            "Splendid",
            "Great",
            "Phew",
        )
    }
}
