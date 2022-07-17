package dev.joshvocal.wordlecompose.ui.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.joshvocal.wordlecompose.data.game.CharacterState
import dev.joshvocal.wordlecompose.data.game.WordGuess
import dev.joshvocal.wordlecompose.data.game.WordleLetter
import dev.joshvocal.wordlecompose.ui.theme.*

@Composable
fun WordleGrid(
    guesses: List<WordGuess>,
) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            guesses.forEach { guess ->
                WordleWordRow(guess = guess)
            }
        }
    }
}

@Composable
fun WordleWordRow(guess: WordGuess) {
    Row {
        guess.forEach { (character, state) ->
            CharacterBox(character = character, characterState = state)
        }
    }
}

@Preview
@Composable
private fun WordleWordRowPreviewLightMode() {
    isSystemInDarkTheme = false

    val guess = arrayOf(
        WordleLetter(character = 'H', state = CharacterState.Correct),
        WordleLetter(character = 'E', state = CharacterState.Correct),
        WordleLetter(character = 'L', state = CharacterState.Present),
        WordleLetter(character = 'L', state = CharacterState.Absent),
        WordleLetter(character = 'O', state = CharacterState.Unknown),
    )

    Row {
        guess.forEach { (character, state) ->
            CharacterBox(character = character, characterState = state)
        }
    }
}

@Preview
@Composable
private fun WordleWordRowPreviewDarkMode() {
    isSystemInDarkTheme = true

    val guess = arrayOf(
        WordleLetter(character = 'H', state = CharacterState.Correct),
        WordleLetter(character = 'E', state = CharacterState.Correct),
        WordleLetter(character = 'L', state = CharacterState.Present),
        WordleLetter(character = 'L', state = CharacterState.Absent),
        WordleLetter(character = 'O', state = CharacterState.Unknown),
    )

    Row {
        guess.forEach { (character, state) ->
            CharacterBox(character = character, characterState = state)
        }
    }
}

@Composable
fun CharacterState.boxColour(): Color = when (this) {
    CharacterState.Correct -> colorCorrect
    CharacterState.Present -> colorPresent
    CharacterState.Absent -> colorAbsent
    CharacterState.Unknown -> colorTone7
}

@Composable
fun CharacterBox(
    character: Char?,
    characterState: CharacterState,
) {
    val backgroundColour by animateColorAsState(targetValue = characterState.boxColour())
    val (foregroundColor, borderColor) = when {
        characterState == CharacterState.Unknown && character?.isWhitespace() == true ->
            colorBackground to colorTone4
        characterState == CharacterState.Unknown ->
            keyTextColor to colorTone3
        else ->
            tileTextColor to characterState.boxColour()
    }

    Box(
        modifier = Modifier
            .size(48.dp)
            .padding(2.dp)
            .border(BorderStroke(width = 1.dp, color = borderColor))
            .background(color = backgroundColour),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = character.toString(),
            color = foregroundColor,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun CharacterBoxPreviewLightMode() {
    isSystemInDarkTheme = false

    Column {
        CharacterBox(character = 'S', characterState = CharacterState.Correct)
        CharacterBox(character = 'W', characterState = CharacterState.Present)
        CharacterBox(character = 'A', characterState = CharacterState.Absent)
        CharacterBox(character = 'G', characterState = CharacterState.Unknown)
    }
}

@Preview
@Composable
private fun CharacterBoxPreviewDarkMode() {
    isSystemInDarkTheme = true

    Column {
        CharacterBox(character = 'S', characterState = CharacterState.Correct)
        CharacterBox(character = 'W', characterState = CharacterState.Present)
        CharacterBox(character = 'A', characterState = CharacterState.Absent)
        CharacterBox(character = 'G', characterState = CharacterState.Unknown)
    }
}
