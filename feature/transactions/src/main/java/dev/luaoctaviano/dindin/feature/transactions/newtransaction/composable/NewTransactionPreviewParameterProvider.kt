package dev.luaoctaviano.dindin.feature.transactions.newtransaction.composable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.feature.transactions.newtransaction.NewTransactionUiState

class NewTransactionPreviewParameterProvider : PreviewParameterProvider<NewTransactionUiState> {
    private val defaultState = NewTransactionUiState()

    private val filledState = defaultState.copy(
        description = "Despesa de teste",
        amount = "1000",
        isPaid = true,
        associatedAccount = BankAccount(
            name = "Carteira",
            isDefault = false,
            iconId = "IC_WALLET"
        )
    )

    override val values = sequenceOf(
        defaultState, filledState
    )
}
