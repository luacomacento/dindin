package dev.luaoctaviano.dindin.feature.transactions.list

import dev.luaoctaviano.dindin.core.data.source.local.dao.DetailedTransaction
import dev.luaoctaviano.dindin.core.ui.state.UiState

data class TransactionListUiState(
    val transactionList: List<DetailedTransaction> = emptyList()
) : UiState