package dev.joshvocal.wordlecompose.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import dev.joshvocal.wordlecompose.ui.theme.accentColor
import dev.joshvocal.wordlecompose.ui.theme.colorTone4
import dev.joshvocal.wordlecompose.ui.theme.keyTextColor


@Composable
fun SettingsSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(
        checked = checked,
        onCheckedChange = { value ->
            onCheckedChange(value)
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = accentColor,
            uncheckedThumbColor = colorTone4,
            checkedTrackColor = accentColor,
            uncheckedTrackColor = colorTone4,
        )
    )
}

@Composable
fun SettingsListItem(
    label: String,
    hint: String = "",
    content: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.body2,
                color = keyTextColor,
                fontWeight = FontWeight.Bold,
            )
        }

        content()
    }

    if (hint.isNotBlank()) {
        Text(
            text = hint,
            style = MaterialTheme.typography.caption,
            color = keyTextColor,
        )
    }
}
