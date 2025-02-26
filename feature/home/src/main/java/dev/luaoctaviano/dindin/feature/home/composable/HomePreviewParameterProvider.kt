package dev.luaoctaviano.dindin.feature.home.composable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountAtDate
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.ui.extension.CoreIcons
import dev.luaoctaviano.dindin.feature.home.HomeHeaderUiState
import dev.luaoctaviano.dindin.feature.home.HomeUiState

class HomePreviewParameterProvider : PreviewParameterProvider<HomeUiState> {
    private val defaultHeader = HomeHeaderUiState(
        totalBalance = 160590L,
        monthIncome = 9999L,
        monthExpense = 8756L,
    )

    private val defaultAccountList = listOf(
        BankAccountAtDate(name = "Carteira", icon = CoreIcons.WALLET.id)
    )

    private val defaultState = HomeUiState(
        header = defaultHeader,
        hideValues = false,
        bankAccounts = defaultAccountList,
    )

    private val hiddenState = defaultState.copy(
        hideValues = true
    )

    override val values = sequenceOf(
        defaultState, hiddenState
    )
}
