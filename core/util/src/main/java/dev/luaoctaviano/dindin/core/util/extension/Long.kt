package dev.luaoctaviano.dindin.core.util.extension

import dev.luaoctaviano.dindin.core.util.constant.StringConstants
import java.text.NumberFormat

fun Long.asCurrency(
    hidden: Boolean = false,
    showDecimals: Boolean = true
): String {
    if (hidden) return StringConstants.HIDDEN

    val numberFormatter = NumberFormat.getCurrencyInstance()
    numberFormatter.minimumFractionDigits = if (showDecimals) 2 else 0
    numberFormatter.maximumFractionDigits = if (showDecimals) 2 else 0
    val value = this.toDouble() / 100
    return numberFormatter.format(value)
}