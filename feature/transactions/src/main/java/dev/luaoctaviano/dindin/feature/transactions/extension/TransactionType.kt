package dev.luaoctaviano.dindin.feature.transactions.extension

import androidx.compose.runtime.Composable
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import dev.luaoctaviano.dindin.core.ui.theme.DinDinTheme
import dev.luaoctaviano.dindin.feature.transactions.R

@Composable
fun TransactionType.getBackgroundColor() =
    when (this) {
        TransactionType.EXPENSE -> DinDinTheme.colors.expenseRedContainer
        TransactionType.INCOME -> DinDinTheme.colors.incomeGreenContainer
        TransactionType.TRANSFER -> DinDinTheme.colors.transferBlueContainer
    }

fun TransactionType.getDescription() =
    when (this) {
        TransactionType.EXPENSE -> R.string.description_expense
        TransactionType.INCOME -> R.string.description_income
        TransactionType.TRANSFER -> R.string.description_transfer
    }

fun TransactionType.getScreenTitleString() =
    when (this) {
        TransactionType.EXPENSE -> R.string.title_new_expense
        TransactionType.INCOME -> R.string.title_new_income
        TransactionType.TRANSFER -> R.string.title_new_transfer
    }