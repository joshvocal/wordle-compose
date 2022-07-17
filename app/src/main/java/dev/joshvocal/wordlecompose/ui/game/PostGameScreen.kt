package dev.joshvocal.wordlecompose.ui.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Replay
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.joshvocal.wordlecompose.data.game.WordleGameState
import dev.joshvocal.wordlecompose.ui.common.Toast
import dev.joshvocal.wordlecompose.ui.theme.keyTextColor
import java.util.concurrent.TimeUnit
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter

@Composable
fun PostGameScreen(state: WordleGameViewState, viewModel: WordleGameViewModel) {
    if (state.game.state == WordleGameState.Won) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Toast(
                text = state.game.victoryMessage.uppercase(),
            )

            IconButton(
                onClick = viewModel::restartGame,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Replay,
                    contentDescription = "Replay",
                    tint = keyTextColor,
                )
            }
        }

        KonfettiView(
            modifier = Modifier.fillMaxSize(),
            parties = listOf(
                Party(
                    position = Position.Relative(0.5, 0.1),
                    emitter = Emitter(duration = 5, TimeUnit.SECONDS).perSecond(30)
                )
            ),
        )
    } else if (state.game.state == WordleGameState.Lost) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Toast(
                text = state.game.targetWord.uppercase(),
            )

            IconButton(
                onClick = viewModel::restartGame,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Replay,
                    contentDescription = "Replay",
                    tint = keyTextColor,
                )
            }
        }
    }
}
