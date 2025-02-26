package dev.luaoctaviano.dindin.core.ui.extension

import dev.luaoctaviano.dindin.core.util.enums.TransactionType

fun Boolean.getIsPaidDescription(type: TransactionType) =
    when (type) {
        TransactionType.EXPENSE -> {
            if (this) CoreStrings.description_paid else CoreStrings.description_unpaid
        }

        TransactionType.INCOME -> {
            if (this) CoreStrings.description_received else CoreStrings.description_unreceived
        }

        TransactionType.TRANSFER -> {
            if (this) CoreStrings.description_transfered else CoreStrings.description_untransfered
        }
    }