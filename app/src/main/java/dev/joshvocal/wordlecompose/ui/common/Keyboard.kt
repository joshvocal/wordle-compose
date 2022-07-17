package dev.joshvocal.wordlecompose.ui.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.joshvocal.wordlecompose.data.game.CharacterState
import dev.joshvocal.wordlecompose.data.game.Keyboard
import dev.joshvocal.wordlecompose.ui.theme.*

fun CharacterState.keyBackgroundColor(): Color = when (this) {
    CharacterState.Unknown -> keyBg
    CharacterState.Correct -> keyBgCorrect
    CharacterState.Present -> keyBgPresent
    CharacterState.Absent -> keyBgAbsent
}

fun CharacterState.keyForegroundColor(): Color = when (this) {
    CharacterState.Unknown -> keyTextColor
    else -> keyEvaluatedTextColor
}

@Preview
@Composable
fun KeyboardPreviewLightMode() {
    isSystemInDarkTheme = false

    WordleKeyboard(
        keyboard = Keyboard(),
        onKey = {},
        onBackspace = {},
        onEnter = {},
        enabled = true,
    )
}

@Preview
@Composable
fun KeyboardPreviewDarkMode() {
    isSystemInDarkTheme = true

    WordleKeyboard(
        keyboard = Keyboard(),
        onKey = {},
        onBackspace = {},
        onEnter = {},
        enabled = true,
    )
}


@Composable
fun WordleKeyboard(
    keyboard: Keyboard,
    enabled: Boolean,
    onKey: (char: Char) -> Unit,
    onBackspace: () -> Unit,
    onEnter: () -> Unit,
) {
    val keys = keyboard.keys.keys.zip(keyboard.keys.values)

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                for (index in 0..9) {
                    val (letter, state) = keys[index]
                    Key(enabled = enabled, letter = letter, state = state, onClick = onKey)
                }
            }

            Row(horizontalArrangement = Arrangement.SpaceAround) {
                for (index in 10..18) {
                    val (letter, state) = keys[index]
                    Key(enabled = enabled, letter = letter, state = state, onClick = onKey)
                }
            }

            Row(horizontalArrangement = Arrangement.SpaceAround) {
                SpecialKey(
                    string = "ENTER",
                    enabled = enabled,
                    state = CharacterState.Unknown,
                    onClick = onEnter,
                    textStyle = MaterialTheme.typography.body2
                )

                for (index in 19..25) {
                    val (letter, state) = keys[index]
                    Key(enabled = enabled, letter = letter, state = state, onClick = onKey)
                }

                SpecialKey(
                    string = "⌫",
                    enabled = enabled,
                    state = CharacterState.Unknown,
                    onClick = onBackspace,
                    textStyle = MaterialTheme.typography.body1,
                )
            }
        }
    }
}

@Preview
@Composable
fun KeyPreviewLightMode() {
    isSystemInDarkTheme = false

    Row {
        Key(letter = 'L', state = CharacterState.Correct)
        Key(letter = 'G', state = CharacterState.Present)
        Key(letter = 'H', state = CharacterState.Absent)
        Key(letter = 'T', state = CharacterState.Unknown)
    }
}

@Preview
@Composable
fun KeyPreviewDarkMode() {
    isSystemInDarkTheme = true

    Row {
        Key(letter = 'D', state = CharacterState.Correct)
        Key(letter = 'A', state = CharacterState.Present)
        Key(letter = 'R', state = CharacterState.Absent)
        Key(letter = 'K', state = CharacterState.Unknown)
    }
}

@Composable
fun Key(
    enabled: Boolean = true,
    letter: Char,
    state: CharacterState,
    onClick: (char: Char) -> Unit = {},
) {
    val backgroundColor by animateColorAsState(state.keyBackgroundColor())
    val foregroundColor by animateColorAsState(state.keyForegroundColor())

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(width = 36.dp, height = 48.dp)
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .clip(MaterialTheme.shapes.small)
            .background(backgroundColor)
            .clickable(enabled = enabled) { onClick(letter) },
    ) {
        Text(
            letter.toString(),
            color = foregroundColor,
            style = MaterialTheme.typography.button,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun SpecialKeyPreviewLightMode() {
    isSystemInDarkTheme = false

    Row {
        SpecialKey(
            string = "⌫",
            state = CharacterState.Unknown,
            onClick = {},
            textStyle = MaterialTheme.typography.body1
        )
        SpecialKey(string = "ENTER", state = CharacterState.Unknown, onClick = {})
    }
}

@Preview
@Composable
fun SpecialKeyPreviewDarkMode() {
    isSystemInDarkTheme = true

    Row {
        SpecialKey(
            string = "⌫",
            state = CharacterState.Unknown,
            onClick = {},
            textStyle = MaterialTheme.typography.body1
        )
        SpecialKey(string = "ENTER", state = CharacterState.Unknown, onClick = {})
    }
}

@Composable
fun SpecialKey(
    enabled: Boolean = true,
    string: String,
    state: CharacterState,
    onClick: () -> Unit = {},
    textStyle: TextStyle = MaterialTheme.typography.button,
) {
    val backgroundColor by animateColorAsState(state.keyBackgroundColor())
    val foregroundColor by animateColorAsState(state.keyForegroundColor())

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(width = 60.dp)
            .height(height = 48.dp)
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .clip(MaterialTheme.shapes.small)
            .background(backgroundColor)
            .clickable(enabled = enabled) { onClick() },
    ) {
        Text(
            string,
            color = foregroundColor,
            style = textStyle,
            fontWeight = FontWeight.Bold
        )
    }
}
