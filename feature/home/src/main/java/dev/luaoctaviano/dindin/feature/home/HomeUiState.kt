package dev.luaoctaviano.dindin.feature.home

import dev.luaoctaviano.dindin.core.ui.state.UiState

data class HomeUiState(
    val header: HomeHeaderUiState,
    val hideValues: Boolean,
) : UiState

data class HomeHeaderUiState(
    val totalBalance: Long,
    val monthIncome: Long,
    val monthExpense: Long,
) : UiState
