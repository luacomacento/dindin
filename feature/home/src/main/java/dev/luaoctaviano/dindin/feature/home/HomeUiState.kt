package dev.luaoctaviano.dindin.feature.home

import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountAtDate
import dev.luaoctaviano.dindin.core.data.source.local.dao.DetailedTransaction
import dev.luaoctaviano.dindin.core.ui.state.UiState

data class HomeUiState(
    val header: HomeHeaderUiState = HomeHeaderUiState(),
    val hideValues: Boolean = false,
    val bankAccounts: List<BankAccountAtDate> = emptyList(),
    val lastFiveTransactions: List<DetailedTransaction> = emptyList()
) : UiState

data class HomeHeaderUiState(
    val totalBalance: Long = 0L,
    val monthIncome: Long = 0L,
    val monthExpense: Long = 0L,
) : UiState
