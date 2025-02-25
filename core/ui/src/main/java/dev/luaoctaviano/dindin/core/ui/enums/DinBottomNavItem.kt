package dev.luaoctaviano.dindin.core.ui.enums

import androidx.annotation.DrawableRes
import dev.luaoctaviano.dindin.core.ui.extension.AppRoute
import dev.luaoctaviano.dindin.core.ui.extension.CoreDrawables

sealed class DinBottomNavItem(
    val route: AppRoute,
    @DrawableRes
    val icon: Int,
) {
    data object Home : DinBottomNavItem(AppRoute.HOME, CoreDrawables.ic_home)
    data object Transactions : DinBottomNavItem(AppRoute.TRANSACTIONS, CoreDrawables.ic_list)
    data object NewTransaction : DinBottomNavItem(AppRoute.NEW_TRANSACTION, CoreDrawables.ic_add)
    data object BankAccounts : DinBottomNavItem(AppRoute.BANK_ACCOUNTS, CoreDrawables.ic_bank)
    data object Categories : DinBottomNavItem(AppRoute.CATEGORIES, CoreDrawables.ic_tag)

    companion object {
        fun entries() = listOf(
            Home,
            Transactions,
            NewTransaction,
            BankAccounts,
            Categories,
        )
    }
}