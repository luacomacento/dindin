package dev.luaoctaviano.dindin.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.luaoctaviano.dindin.core.data.repository.TransactionRepository
import dev.luaoctaviano.dindin.core.domain.GetAccountListAtDateUseCase
import dev.luaoctaviano.dindin.core.domain.GetTotalBalanceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAccountListAtDateUseCase: GetAccountListAtDateUseCase,
    private val getTotalBalanceUseCase: GetTotalBalanceUseCase,
    private val transactionRepository: TransactionRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())

    val uiState: StateFlow<HomeUiState>
        get() = _uiState.stateIn(
            viewModelScope, SharingStarted.Eagerly, HomeUiState()
        )

    init {
        getTotalBalance()
        getAccountsAtDate()
        getLastFiveTransactions()
    }

    private fun getTotalBalance() {
        viewModelScope.launch {
            getTotalBalanceUseCase(Date()).collect {
                _uiState.value = _uiState.value.copy(
                    header = _uiState.value.header.copy(
                        totalBalance = it
                    )
                )
            }
        }
    }

    private fun getAccountsAtDate() {
        viewModelScope.launch {
            getAccountListAtDateUseCase(Date()).collect {
                _uiState.value = _uiState.value.copy(
                    bankAccounts = it
                )
            }
        }
    }

    private fun getLastFiveTransactions() {
        viewModelScope.launch {
            transactionRepository.getLastFiveTransactions().collect {
                _uiState.value = _uiState.value.copy(
                    lastFiveTransactions = it
                )
            }
        }
    }

    fun toggleHiddenValues() {
        _uiState.value = _uiState.value.copy(
            hideValues = _uiState.value.hideValues.not()
        )
    }

    fun deleteTransaction(transactionId: Long) {
        viewModelScope.launch {
            transactionRepository.deleteTransaction(transactionId)
        }
    }
}