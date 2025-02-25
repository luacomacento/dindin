package dev.luaoctaviano.dindin.core.ui.extension

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.luaoctaviano.dindin.core.ui.theme.DinDinTheme

@Composable
fun Long.getCurrencyTextColor(hidden: Boolean) =
    when {
        hidden -> MaterialTheme.colorScheme.onSurface
        this >= 0L -> DinDinTheme.colors.incomeGreen
        else -> DinDinTheme.colors.expenseRed
    }