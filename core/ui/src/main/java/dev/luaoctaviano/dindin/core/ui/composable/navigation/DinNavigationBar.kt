package dev.luaoctaviano.dindin.core.ui.composable.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import dev.luaoctaviano.dindin.core.ui.enums.DinBottomNavItem
import dev.luaoctaviano.dindin.core.ui.extension.AppRoute
import dev.luaoctaviano.dindin.core.ui.extension.isMainItem
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.core.ui.theme.DinDinTheme

@Composable
fun DinNavigationBar(
    currentDestination: NavDestination?,
    listener: NavBarListener,
) {
    val hapticFeedback = LocalHapticFeedback.current

    Column {
        HorizontalDivider(color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
            windowInsets = NavigationBarDefaults.windowInsets,
            tonalElevation = 0.dp,
        ) {
            Row(
                modifier =
                    Modifier
                        .padding(horizontal = Dimens.small)
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.small),
            ) {
                DinBottomNavItem.entries().forEach {
                    DinNavigationBarItem(
                        modifier = Modifier.weight(1F),
                        selected = currentDestination?.route == it.route.name,
                        onClick = {
                            if (it.isMainItem()) {
                                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                            }
                            listener.onItemClick(it.route)
                        },
                        isMainItem = it.isMainItem(),
                        icon = {
                            Icon(
                                painter = painterResource(it.icon),
                                contentDescription = null,
                            )
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DinNavigationBarPreview() {
    DinDinTheme {
        DinNavigationBar(
            currentDestination = null,
            listener = object : NavBarListener {
                override fun onItemClick(itemRoute: AppRoute) = Unit
            }
        )
    }
}