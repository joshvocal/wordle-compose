package dev.joshvocal.wordlecompose.data.game

sealed class WordleGameState {
    object Playing : WordleGameState()
    object Won : WordleGameState()
    object Lost : WordleGameState()
}

enum class CharacterState(val rank: Int) {
    Correct(rank = 3),
    Present(rank = 2),
    Absent(rank = 1),
    Unknown(rank = 0),
}

enum class InputState {
    NotEnoughLetters,
    NotInWordList,
    Valid,
}

data class WordleLetter(
    val character: Char = ' ',
    val state: CharacterState = CharacterState.Unknown,
)

typealias WordGuess = Array<WordleLetter>

fun WordGuess.isCorrect(): Boolean = all { wordleCharacter ->
    wordleCharacter.state == CharacterState.Correct
}

fun WordGuess.getStates(): Array<CharacterState> = map { wordleCharacter ->
    wordleCharacter.state
}.toTypedArray()
