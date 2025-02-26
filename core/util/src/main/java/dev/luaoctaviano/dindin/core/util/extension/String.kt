package dev.luaoctaviano.dindin.core.util.extension

import dev.luaoctaviano.dindin.core.util.enums.TransactionType

fun String.toTypedCurrencyLong(type: TransactionType): Long? {
    val longResult = this.filter { it.isDigit() }.toLongOrNull()

    return if (type == TransactionType.EXPENSE) {
        longResult?.let { -it }
    } else {
        longResult
    }
}