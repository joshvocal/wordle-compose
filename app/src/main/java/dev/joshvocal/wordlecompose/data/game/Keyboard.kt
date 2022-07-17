package dev.joshvocal.wordlecompose.data.game

data class Keyboard(
    val keys: LinkedHashMap<Char, CharacterState> = KeyboardLayout.english.associateWith {
        CharacterState.Unknown
    } as LinkedHashMap<Char, CharacterState>
)

object KeyboardLayout {
    val english = listOf(
        'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P',
        'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L',
        'Z', 'X', 'C', 'V', 'B', 'N', 'M'
    )
}
