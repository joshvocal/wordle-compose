package dev.joshvocal.wordlecompose.ui.theme

import androidx.compose.ui.graphics.Color

// Colors
val green = Color(0xFF6AAA64)
val darkendGreen = Color(0xFF538D4E)
val yellow = Color(0xFFC9B458)
val darkendYellow = Color(0xFFB59F3B)
val lightGray = Color(0xFFD3D6DA)
val gray = Color(0xFF86888A)
val gray2 = Color(0xFFDCDCDC)
val gray3 = Color(0xFFDFDFDF)
val darkGray = Color(0xFF939598)
val white = Color(0xFFFFFFFF)
val black = Color(0xFF212121)
val black2 = Color(0xFF121212)
val black3 = Color(0xFF363636)
val orange = Color(0xFFf5793A)
val blue = Color(0xFF85C0f9)

val accentColor: Color
    get() = if (isHighContrastMode)
        orange
    else
        green

val accentColorVariant: Color
    get() = if (isHighContrastMode)
        orange
    else
        darkendGreen

// Color Tones
val colorTone1: Color
    get() = if (isSystemInDarkTheme)
        white
    else
        Color(0xFF000000)

val colorTone2: Color
    get() = if (isSystemInDarkTheme)
        Color(0xFF818384)
    else
        Color(0xFF787C7E)

val colorTone3: Color
    get() = if (isSystemInDarkTheme)
        Color(0xFF565758)
    else
        Color(0xFF878A8C)

val colorTone4: Color
    get() = if (isSystemInDarkTheme)
        Color(0xFF3A3A3C)
    else
        Color(0xFFD3D6DA)

val colorTone5: Color
    get() = if (isSystemInDarkTheme)
        Color(0xFF272729)
    else
        Color(0xFFEDEFF1)

val colorTone6: Color
    get() = if (isSystemInDarkTheme)
        Color(0xFF1A1A1B)
    else
        Color(0xFFF6f7F8)

val colorTone7: Color
    get() = if (isSystemInDarkTheme)
        Color(0xFF121213)
    else
        Color(0xFFFFFFFF)

val colorTone8: Color
    get() = if (isSystemInDarkTheme)
        Color(0xFFFFFFFF)
    else
        Color(0xFF121212)

val colorTone9: Color
    get() = if (isSystemInDarkTheme)
        Color(0xFF424242)
    else
        Color(0xFFDFDFDF)

val colorTone10: Color
    get() = if (isSystemInDarkTheme)
        Color(0xFFDFDFDF)
    else
        Color(0xFF000000)

val colorTone11: Color
    get() = if (isSystemInDarkTheme)
        Color(0xFFDFDFDF)
    else
        Color(0xFF787C7E)

val colorTone12: Color
    get() = if (isSystemInDarkTheme)
        Color(0xFFDFDFDF)
    else
        Color(0xFF363636)

// Color States
val colorPresent: Color
    get() = when {
        isHighContrastMode -> blue
        isSystemInDarkTheme -> darkendYellow
        else -> yellow
    }
val colorCorrect: Color
    get() = when {
        isHighContrastMode -> orange
        isSystemInDarkTheme -> darkendGreen
        else -> green
    }

val colorAbsent: Color
    get() = when {
        isHighContrastMode || isSystemInDarkTheme -> colorTone4
        else -> colorTone2
    }

// Keys and Tiles
val tileTextColor: Color
    get() = if (isSystemInDarkTheme)
        colorTone1
    else
        colorTone7

val keyTextColor: Color get() = colorTone1

val keyEvaluatedTextColor: Color
    get() = if (isSystemInDarkTheme)
        colorTone1
    else
        colorTone7

val keyBg: Color
    get() = if (isSystemInDarkTheme)
        colorTone2
    else
        colorTone4

val keyBgPresent: Color get() = colorPresent

val keyBgCorrect: Color get() = colorCorrect

val keyBgAbsent: Color get() = colorAbsent

val modalContentBg: Color get() = colorTone7

val colorBackground: Color get() = colorTone7
