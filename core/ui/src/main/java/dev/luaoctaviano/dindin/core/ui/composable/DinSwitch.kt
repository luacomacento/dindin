package dev.luaoctaviano.dindin.core.ui.composable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun DinSwitch(
    checked: Boolean,
    enabled: Boolean = true,
    tint: Color? = null,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(
        checked = checked,
        enabled = enabled,
        onCheckedChange = onCheckedChange,
        colors =
            tint?.let {
                SwitchDefaults.colors(
                    checkedTrackColor = it,
                    checkedThumbColor = MaterialTheme.colorScheme.surface,
                    uncheckedTrackColor = it.copy(0.08F),
                    uncheckedBorderColor = it.copy(0.2F),
                    uncheckedThumbColor = it.copy(0.2F),
                )
            } ?: SwitchDefaults.colors(),
    )
}
