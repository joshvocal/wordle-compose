package dev.joshvocal.wordlecompose.ui.game

import dev.joshvocal.wordlecompose.data.game.Keyboard
import dev.joshvocal.wordlecompose.data.game.WordGuess
import dev.joshvocal.wordlecompose.data.game.WordleGame
import java.util.LinkedList
import java.util.Queue

data class WordleGameViewState(
    val game: WordleGame = WordleGame(
        targetWord = "hello",
        dictionary = listOf(),
        guesses = listOf(),
        userInput = "",
    ),
    val grid: List<WordGuess> = emptyList(),
    val keyboard: Keyboard = Keyboard(),
    val areActionsEnabled: Boolean = true,
    val userPrompts: Queue<String> = LinkedList(),
    val requestedDialogRequest: DialogRequest? = null
)

enum class DialogRequest {
    Settings,
    Help,
}
