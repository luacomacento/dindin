package dev.luaoctaviano.dindin.feature.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class HomePreviewParameterProvider : PreviewParameterProvider<HomeUiState> {
    private val defaultHeader = HomeHeaderUiState(
        totalBalance = 160590L,
        monthIncome = 9999L,
        monthExpense = 8756L,
    )

    val defaultState = HomeUiState(
        header = defaultHeader,
        hideValues = false,
    )

    private val hiddenState = defaultState.copy(
        hideValues = true
    )

    override val values = sequenceOf(
        defaultState, hiddenState
    )
}
