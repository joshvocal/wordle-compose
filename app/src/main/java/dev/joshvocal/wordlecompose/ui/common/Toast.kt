package dev.joshvocal.wordlecompose.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.joshvocal.wordlecompose.ui.game.WordleGameViewModel
import dev.joshvocal.wordlecompose.ui.game.WordleGameViewState
import dev.joshvocal.wordlecompose.ui.theme.colorTone1
import dev.joshvocal.wordlecompose.ui.theme.colorTone7
import dev.joshvocal.wordlecompose.ui.theme.isSystemInDarkTheme
import kotlinx.coroutines.delay

@Composable
fun ToastScreen(viewModel: WordleGameViewModel, state: WordleGameViewState) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        state.userPrompts.forEach { prompt ->
            AutoDismissToast(
                text = prompt,
                modifier = Modifier.padding(top = 80.dp),
                onDismiss = viewModel::removeUserPrompt,
            )
        }
    }
}

@Composable
fun AutoDismissToast(
    text: String,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(text) {
        delay(1000)
        visible = false
        onDismiss()
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Toast(text = text, modifier = modifier)
    }
}

@Preview
@Composable
fun ToastPreviewLightMode() {
    isSystemInDarkTheme = false
    Toast(text = "Not in word list")
}

@Preview
@Composable
fun ToastPreviewDarkMode() {
    isSystemInDarkTheme = true
    Toast(text = "Not in word list")
}

@Composable
fun Toast(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = colorTone1)
            .padding(8.dp),
        color = colorTone7,
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Bold,
    )
}
