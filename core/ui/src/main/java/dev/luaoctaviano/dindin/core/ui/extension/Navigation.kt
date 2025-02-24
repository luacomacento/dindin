package dev.luaoctaviano.dindin.core.ui.extension

import androidx.navigation.NavDestination

enum class NavRoutes {
    HOME,
    TRANSACTIONS,
    BANK_ACCOUNTS,
    CATEGORIES
}

fun NavDestination?.isHome() = this?.route == NavRoutes.HOME.name
fun NavDestination?.isTransactions() = this?.route == NavRoutes.TRANSACTIONS.name
fun NavDestination?.isBankAccounts() = this?.route == NavRoutes.BANK_ACCOUNTS.name
fun NavDestination?.isCategories() = this?.route == NavRoutes.CATEGORIES.name
