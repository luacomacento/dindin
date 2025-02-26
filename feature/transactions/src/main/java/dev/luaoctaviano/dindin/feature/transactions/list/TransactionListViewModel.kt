package dev.luaoctaviano.dindin.feature.transactions.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.luaoctaviano.dindin.core.data.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<TransactionListUiState> = MutableStateFlow(TransactionListUiState())

    val uiState: StateFlow<TransactionListUiState>
        get() = _uiState.stateIn(viewModelScope, SharingStarted.Eagerly, TransactionListUiState())

    init {
        getTransactions()
    }

    private fun getTransactions() {
        viewModelScope.launch {
            transactionRepository.getAllTransactions().collect {
                _uiState.value = _uiState.value.copy(
                    transactionList = it
                )
            }
        }
    }

    fun deleteTransaction(transactionId: Long) {
        viewModelScope.launch {
            transactionRepository.deleteTransaction(transactionId)
        }
    }
}