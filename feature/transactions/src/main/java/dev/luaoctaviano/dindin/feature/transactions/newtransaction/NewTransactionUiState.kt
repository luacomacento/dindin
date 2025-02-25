package dev.luaoctaviano.dindin.feature.transactions.newtransaction

import dev.luaoctaviano.dindin.core.data.enums.TransactionType
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category
import dev.luaoctaviano.dindin.core.ui.state.UiState
import dev.luaoctaviano.dindin.core.util.constant.StringConstants

data class NewTransactionUiState(
    val description: String = StringConstants.EMPTY_STRING,
    val transactionType: TransactionType = TransactionType.EXPENSE,
    val value: String = StringConstants.EMPTY_STRING,
    val isPaid: Boolean = false,
    val associatedAccount: BankAccount? = null,
    val category: Category? = null,
    val accountList: List<BankAccount> = emptyList()
) : UiState
