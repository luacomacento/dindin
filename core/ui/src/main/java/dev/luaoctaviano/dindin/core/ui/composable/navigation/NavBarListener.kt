package dev.luaoctaviano.dindin.core.ui.composable.navigation

import dev.luaoctaviano.dindin.core.ui.extension.AppRoute

interface NavBarListener {
    fun onItemClick(itemRoute: AppRoute)
}