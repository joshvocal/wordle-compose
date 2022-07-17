package dev.joshvocal.wordlecompose.ui.game

import dev.joshvocal.wordlecompose.data.game.*
import dev.joshvocal.wordlecompose.ui.base.BaseViewModel

class WordleGameViewModel(
    initialState: WordleGameViewState
) : BaseViewModel<WordleGameViewState>(
    initialState = initialState
) {

    init {
        restartGame()
    }

    fun restartGame() {
        val newGame = WordleGameViewState().copy(
            game = currentState.game.copy(
                dictionary = currentState.game.dictionary,
                guesses = listOf(),
                targetWord = currentState.game.dictionary.random(),
            )
        )

        updateState {
            newGame
        }

        updateGrid()
    }

    private fun updateGameState() {
        when (currentState.game.state) {
            WordleGameState.Playing -> {
                updateState {
                    copy(
                        areActionsEnabled = true
                    )
                }
            }
            WordleGameState.Lost -> {
                updateState {
                    copy(
                        areActionsEnabled = false
                    )
                }
            }
            WordleGameState.Won -> {
                updateState {
                    copy(
                        areActionsEnabled = false
                    )
                }
            }
        }
    }

    private fun updateGrid() {
        val guessNumber = currentState.game.guesses.size
        val numberOfGuessesRemaining = WordleGame.MAX_NUMBER_OF_GUESSES
        val currentGuesses = currentState.game.guesses.toMutableList()

        repeat(numberOfGuessesRemaining - guessNumber) {
            currentGuesses += Array(WordleGame.MAX_WORD_LENGTH) {
                WordleLetter()
            }
        }

        updateState {
            copy(grid = currentGuesses)
        }
    }

    private fun updateRow() {
        val guessNumber = currentState.game.guesses.size
        val currentRow = currentState.grid[guessNumber]
        val updatedGrid = currentState.grid.toMutableList()

        currentState.game.userInput
            .padEnd(length = WordleGame.MAX_WORD_LENGTH)
            .forEachIndexed { index, char ->
                currentRow[index] = WordleLetter(character = char)
            }

        updatedGrid[guessNumber] = currentRow

        updateState {
            copy(grid = updatedGrid)
        }
    }

    private fun updateKeyboard(guess: WordGuess) {
        val keyboard: Keyboard = currentState.keyboard

        guess.forEach { (char: Char, state: CharacterState) ->
            keyboard.keys[char]?.rank?.let { currentState ->
                if (currentState < state.rank) {
                    keyboard.keys[char] = state
                }
            }
        }

        updateState {
            copy(
                keyboard = keyboard
            )
        }
    }

    private fun onClearInput() {
        updateState {
            copy(
                game = currentState.game.copy(
                    userInput = String()
                )
            )
        }
    }

    fun onKeyPress(key: Char) {
        if (currentState.game.inputState == InputState.NotEnoughLetters) {
            updateState {
                copy(
                    game = currentState.game.copy(
                        userInput = currentState.game.userInput + key
                    )
                )
            }

            updateRow()
        }
    }

    fun onBackspace() {
        val currentUserInput = currentState.game.userInput

        val newUserInput = when {
            currentUserInput.isNotEmpty() -> currentUserInput.dropLast(1)
            else -> currentUserInput
        }

        updateState {
            copy(
                game = currentState.game.copy(
                    userInput = newUserInput
                )
            )
        }

        updateRow()
    }

    private fun addUserPrompt(prompt: String) {
        val newUserPrompts = currentState.userPrompts
        newUserPrompts.add(prompt)

        updateState {
            copy(
                userPrompts = newUserPrompts,
            )
        }
    }

    fun removeUserPrompt() {
        val newUserPrompts = currentState.userPrompts
        newUserPrompts.remove()

        updateState {
            copy(
                userPrompts = newUserPrompts
            )
        }
    }

    fun onEnter() {
        val word = currentState.game.userInput
        val dictionary = currentState.game.dictionary

        when (Validator.isWordValid(word = word, dictionary = dictionary)) {
            InputState.NotEnoughLetters -> guessNotEnoughLetters()
            InputState.NotInWordList -> guessNotInWordList()
            InputState.Valid -> guessIsValid()
        }
    }

    private fun guessNotEnoughLetters() {
        addUserPrompt("Not enough letters")
        updateGrid()
        updateRow()
    }

    private fun guessNotInWordList() {
        addUserPrompt("Not in word list")
        updateGrid()
        updateRow()
    }

    private fun guessIsValid() {
        val currentGuess = currentState.game.userInput
        val currentGuesses = currentState.game.guesses
        val targetWord = currentState.game.targetWord

        val newGuess: WordGuess = Validator.validateGuess(
            guess = currentGuess, target = targetWord
        )
        val updatedGuesses: List<WordGuess> = currentGuesses + listOf(newGuess)

        updateState {
            copy(
                game = currentState.game.copy(
                    guesses = updatedGuesses
                )
            )
        }

        updateKeyboard(guess = newGuess)
        updateGrid()
        updateGameState()
        onClearInput()
    }

    fun requestDialog(dialogRequest: DialogRequest) {
        updateState {
            copy(
                requestedDialogRequest = dialogRequest
            )
        }
    }

    fun closeDialog() {
        updateState {
            copy(
                requestedDialogRequest = null
            )
        }
    }
}
