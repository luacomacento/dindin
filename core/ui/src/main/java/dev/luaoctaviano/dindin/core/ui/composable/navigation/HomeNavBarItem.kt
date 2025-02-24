package dev.luaoctaviano.dindin.core.ui.composable.navigation

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import dev.luaoctaviano.dindin.core.ui.R

@Composable
fun HomeNavBarItem(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    DinNavigationBarItem(
        modifier = modifier,
        selected = isSelected,
        onClick = onClick,
        icon = {
            Icon(
                painter = painterResource(R.drawable.ic_home),
                contentDescription = null,
            )
        },
    )
}
