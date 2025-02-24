package dev.luaoctaviano.dindin.feature.home

import dev.luaoctaviano.dindin.core.data.enums.TransactionType
import dev.luaoctaviano.feature.home.R

typealias HomeDrawables = R.drawable

fun TransactionType.getHomeHeaderArrow() =
    when (this) {
        TransactionType.INCOME -> HomeDrawables.ic_double_arrow_up
        TransactionType.EXPENSE -> HomeDrawables.ic_double_arrow_down
        else -> null
    }

fun getVisibilityIcon(hidden: Boolean) =
    if (hidden) HomeDrawables.ic_visibility else HomeDrawables.ic_visibility_off