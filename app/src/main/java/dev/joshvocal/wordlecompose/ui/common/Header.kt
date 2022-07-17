package dev.joshvocal.wordlecompose.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.joshvocal.wordlecompose.ui.theme.colorTone4
import dev.joshvocal.wordlecompose.ui.theme.colorTone7
import dev.joshvocal.wordlecompose.ui.theme.isSystemInDarkTheme
import dev.joshvocal.wordlecompose.ui.theme.keyTextColor

@Preview
@Composable
fun GameHeaderPreviewLight() {
    isSystemInDarkTheme = false

    GameHeader(modifier = Modifier.fillMaxWidth())
}

@Preview
@Composable
fun GameHeaderPreviewDark() {
    isSystemInDarkTheme = true

    GameHeader(modifier = Modifier.fillMaxWidth())
}

@Composable
fun GameHeader(
    modifier: Modifier,
    enabled: Boolean = true,
    onSettingsClicked: () -> Unit = {},
    onHowToPlayClicked: () -> Unit = {},
) {
    Column(
        Modifier.background(color = colorTone7)
    ) {
        Box(
            modifier = modifier.padding(bottom = 8.dp)
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                IconButton(
                    onClick = onHowToPlayClicked,
                    enabled = enabled,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.HelpOutline,
                        contentDescription = "Help",
                        tint = keyTextColor,
                    )
                }
            }

            Box(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = "Wordle",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = keyTextColor,
                )
            }

            Row(
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {

                IconButton(
                    onClick = onSettingsClicked,
                    enabled = enabled,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "Settings",
                        tint = keyTextColor,
                    )
                }
            }
        }

        Divider(color = colorTone4)
    }
}
