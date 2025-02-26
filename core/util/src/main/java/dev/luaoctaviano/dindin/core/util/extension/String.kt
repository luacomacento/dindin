package dev.luaoctaviano.dindin.core.util.extension

import dev.luaoctaviano.dindin.core.util.enums.TransactionType

fun String.toTypedCurrencyLong(type: TransactionType) =
    if (type == TransactionType.EXPENSE) {
        -this.filter { it.isDigit() }.toLong()
    } else {
        this.filter { it.isDigit() }.toLong()
    }