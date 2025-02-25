package dev.luaoctaviano.dindin.core.ui.composable.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.luaoctaviano.dindin.core.ui.extension.buttonPushDownAnim
import dev.luaoctaviano.dindin.core.ui.theme.Dimens


@Composable
internal fun DinNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isMainItem: Boolean = false,
    icon: @Composable () -> Unit,
    enabled: Boolean = true,
    selectedContentColor: Color = MaterialTheme.colorScheme.primary,
) {
    val backgroundColor =
        if (isMainItem) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            Color.Transparent
        }

    val defaultModifier = if (isMainItem) Modifier.buttonPushDownAnim(enabled) else modifier

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.tiny),
    ) {
        Box(
            modifier =
                defaultModifier
                    .defaultMinSize(minHeight = 40.dp)
                    .fillMaxWidth()
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(Dimens.extraLarge),
                    )
                    .clip(RoundedCornerShape(Dimens.extraLarge))
                    .clickable {
                        if (enabled) {
                            onClick()
                        }
                    },
            contentAlignment = Alignment.Center,
        ) {
            Surface(
                color = Color.Transparent,
                contentColor =
                    if (selected) {
                        selectedContentColor
                    } else {
                        MaterialTheme.colorScheme
                            .onSurfaceVariant
                            .copy(
                                alpha = 0.7F,
                            )
                    },
            ) {
                icon()
            }
        }
        Box(
            modifier = Modifier.height(6.dp),
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = selected,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(6.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = CircleShape,
                            ),
                )
            }
        }
    }
}