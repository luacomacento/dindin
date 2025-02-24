package dev.luaoctaviano.dindin.core.ui.composable.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import dev.luaoctaviano.dindin.core.ui.extension.isBankAccounts
import dev.luaoctaviano.dindin.core.ui.extension.isCategories
import dev.luaoctaviano.dindin.core.ui.extension.isHome
import dev.luaoctaviano.dindin.core.ui.extension.isTransactions
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.core.ui.theme.DinDinTheme

@Composable
fun DinNavigationBar(
    currentDestination: NavDestination?,
    onNewTransactionClick: () -> Unit,
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
                HomeNavBarItem(
                    modifier = Modifier.weight(1F),
                    isSelected = currentDestination.isHome(),
                ) {
//                    navController.navigateTo(currentDestination?.route, NavRoutes.HOME.name)
                }

                TransactionsNavBarItem(
                    modifier = Modifier.weight(1F),
                    isSelected = currentDestination.isTransactions(),
                ) {
//                    navController.navigateTo(currentDestination?.route, NavRoutes.TRANSACTIONS.name)
                }

                NewTransactionNavBarItem(
                    modifier = Modifier.weight(1F),
                ) {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    onNewTransactionClick.invoke()
                }

                BankAccountsNavBarItem(
                    modifier = Modifier.weight(1F),
                    isSelected = currentDestination.isBankAccounts(),
                ) {
                    // Not available
                }

                CategoriesNavBarItem(
                    modifier = Modifier.weight(1F),
                    isSelected = currentDestination.isCategories(),
                ) {
                    // Not available
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
            onNewTransactionClick = {}
        )
    }
}