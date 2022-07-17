package dev.joshvocal.wordlecompose.ui.help

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.joshvocal.wordlecompose.data.game.CharacterState
import dev.joshvocal.wordlecompose.data.game.WordleGame
import dev.joshvocal.wordlecompose.data.game.WordleLetter
import dev.joshvocal.wordlecompose.ui.common.WordleWordRow
import dev.joshvocal.wordlecompose.ui.theme.colorTone4
import dev.joshvocal.wordlecompose.ui.theme.keyTextColor

@Composable
fun HowToPlayScreen(
    maxNumberOfTries: Int = WordleGame.MAX_NUMBER_OF_GUESSES,
    maxWordLength: Int = WordleGame.MAX_WORD_LENGTH,
) {
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        BoldedSentence("Guess the WORDLE in $maxNumberOfTries tries.", boldedWord = "WORDLE")
        Sentence("Each guess must be a valid $maxWordLength-letter word. Hit the enter button to submit.")
        Sentence("After each guess, the color of the tiles will change to show how close your guess was to the world")
        Divider(color = colorTone4)
        Sentence("Examples", fontWeight = FontWeight.Black)
        WordleExample(word = "WEARY", characterState = CharacterState.Correct, targetLetter = 'W')
        BoldedSentence("The letter W is in the word and in the correct spot.", boldedWord = "W")
        WordleExample(word = "PILLS", characterState = CharacterState.Present, targetLetter = 'I')
        BoldedSentence("The letter I is in the word but in the wrong spot.", boldedWord = "I")
        WordleExample(word = "VAGUE", characterState = CharacterState.Absent, targetLetter = 'U')
        BoldedSentence("The letter U is not in the word in any spot.", boldedWord = "U")
        Divider(color = colorTone4)
        Sentence("A new WORDLE will be available each day!", fontWeight = FontWeight.Black)
    }
}

@Composable
private fun BoldedSentence(
    text: String,
    boldedWord: String,
) {
    val start = text.indexOf(boldedWord)
    val spanStyles = listOf(
        AnnotatedString.Range(
            item = SpanStyle(fontWeight = FontWeight.Black),
            start = start,
            end = start + boldedWord.length
        )
    )

    Text(
        text = AnnotatedString(text = text, spanStyles = spanStyles),
        color = keyTextColor,
        style = MaterialTheme.typography.body2,
    )
}

@Composable
private fun Sentence(
    text: String,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        color = keyTextColor,
        style = MaterialTheme.typography.body2,
        fontWeight = fontWeight,
    )
}

@Composable
private fun WordleExample(word: String, characterState: CharacterState, targetLetter: Char) {
    val guess = Array(5) { WordleLetter() }

    word.forEachIndexed { index, letter ->
        if (letter == targetLetter) {
            guess[index] = WordleLetter(character = letter, state = characterState)
        } else {
            guess[index] = WordleLetter(character = letter)
        }
    }

    WordleWordRow(guess = guess)
}

