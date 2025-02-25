package dev.luaoctaviano.dindin.core.ui.extension

import dev.luaoctaviano.dindin.core.ui.enums.DinBottomNavItem

fun DinBottomNavItem.isMainItem() = this.route == AppRoute.NEW_TRANSACTION