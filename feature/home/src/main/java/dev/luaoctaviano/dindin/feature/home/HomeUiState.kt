package dev.luaoctaviano.dindin.feature.home

import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.ui.state.UiState

data class HomeUiState(
    val header: HomeHeaderUiState = HomeHeaderUiState(),
    val hideValues: Boolean = false,
    val bankAccounts: List<BankAccount> = emptyList()
) : UiState

data class HomeHeaderUiState(
    val totalBalance: Long = 0L,
    val monthIncome: Long = 0L,
    val monthExpense: Long = 0L,
) : UiState
