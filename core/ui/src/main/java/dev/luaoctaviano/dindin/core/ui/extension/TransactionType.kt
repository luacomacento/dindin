package dev.luaoctaviano.dindin.core.ui.extension

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import dev.luaoctaviano.dindin.core.ui.theme.DinDinTheme

@Composable
fun TransactionType.getCurrencyColor(hideValues: Boolean): Color {
    if (hideValues) return MaterialTheme.colorScheme.onSurface

    return when (this) {
        TransactionType.EXPENSE -> DinDinTheme.colors.expenseRed
        TransactionType.INCOME -> DinDinTheme.colors.incomeGreen
        TransactionType.TRANSFER -> DinDinTheme.colors.transferBlue
    }
}