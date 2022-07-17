package dev.joshvocal.wordlecompose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.accompanist.systemuicontroller.rememberSystemUiController

var isSystemInDarkTheme by mutableStateOf(false)
var isHighContrastMode by mutableStateOf(false)

private val DarkColorPalette = darkColors(
    primary = accentColor,
    primaryVariant = accentColorVariant,
    secondary = accentColor,
    background = colorTone7,
    surface = colorTone7,
    onPrimary = white,
    onSecondary = white,
    onBackground = colorTone1,
    onSurface = colorTone1,
)

private val LightColorPalette = lightColors(
    primary = accentColor,
    primaryVariant = accentColorVariant,
    secondary = accentColor,
    background = colorTone7,
    surface = colorTone7,
    onPrimary = white,
    onSecondary = white,
    onBackground = colorTone1,
    onSurface = colorTone1,
)

@Composable
fun WordleComposeTheme(
    darkTheme: Boolean,
    highContrast: Boolean,
    content: @Composable () -> Unit
) {
    isSystemInDarkTheme = darkTheme
    isHighContrastMode = highContrast

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = colorTone7)

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
