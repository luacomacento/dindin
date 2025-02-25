package dev.luaoctaviano.dindin.core.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.luaoctaviano.dindin.core.ui.enums.ButtonType
import dev.luaoctaviano.dindin.core.ui.extension.buttonPushDownAnim
import dev.luaoctaviano.dindin.core.ui.theme.Dimens

@Composable
fun DinButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabledColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentPadding: PaddingValues = PaddingValues(Dimens.large),
    style: ButtonType = ButtonType.FILLED,
    content: @Composable RowScope.() -> Unit,
) {
    val shape = RoundedCornerShape(Dimens.large)
    val defaultModifier =
        modifier.buttonPushDownAnim(enabled).padding(bottom = Dimens.small).fillMaxWidth()

    when (style) {
        ButtonType.FILLED -> {
            Button(
                onClick = onClick,
                modifier = defaultModifier,
                enabled = enabled,
                interactionSource = interactionSource,
                shape = shape,
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = enabledColor,
                        contentColor = MaterialTheme.colorScheme.contentColorFor(enabledColor),
                    ),
                contentPadding = contentPadding,
                content = content,
            )
        }

        ButtonType.OUTLINED -> {
            OutlinedButton(
                onClick = onClick,
                modifier = defaultModifier,
                enabled = enabled,
                interactionSource = interactionSource,
                shape = shape,
                border = BorderStroke(1.dp, enabledColor),
                contentPadding = contentPadding,
                content = content,
            )
        }

        ButtonType.TEXT ->
            TextButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                shape = shape,
                contentPadding = contentPadding,
                content = content,
            )
    }
}
