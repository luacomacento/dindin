package dev.luaoctaviano.dindin.feature.transactions.newtransaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.luaoctaviano.dindin.core.data.enums.TransactionType
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.domain.GetAccountListAtDateUseCase
import dev.luaoctaviano.dindin.core.domain.SetDefaultBankAccountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTransactionViewModel @Inject constructor(
    private val setDefaultBankAccountUseCase: SetDefaultBankAccountUseCase,
    private val getAccountListAtDateUseCase: GetAccountListAtDateUseCase,
) : ViewModel() {

    val uiState: MutableStateFlow<NewTransactionUiState> = MutableStateFlow(NewTransactionUiState())

    init {
        getAccounts()
    }

    private fun getAccounts() {
        viewModelScope.launch {
            getAccountListAtDateUseCase().collect { accountList ->
                uiState.value = uiState.value.copy(
                    accountList = accountList,
                    associatedAccount = accountList.find { it.isDefault }
                )
            }
        }
    }

    fun changeTransactionValueField(newValue: String) {
        uiState.value = uiState.value.copy(
            value = newValue
        )
    }

    fun changeTransactionType(newType: TransactionType) {
        uiState.value = uiState.value.copy(
            transactionType = newType
        )
    }

    fun changeTransactionDescription(newValue: String) {
        uiState.value = uiState.value.copy(
            description = newValue
        )
    }

    fun changeTransactionPaidStatus(isPaid: Boolean) {
        uiState.value = uiState.value.copy(
            isPaid = isPaid
        )
    }

    fun setDefaultAccount(bankAccount: BankAccount) {
        viewModelScope.launch {
            val updatedAccount = setDefaultBankAccountUseCase(bankAccount)
            uiState.value = uiState.value.copy(
                associatedAccount = updatedAccount
            )
        }
    }

    fun changeAssociatedAccount(bankAccount: BankAccount?) {
        uiState.value = uiState.value.copy(
            associatedAccount = bankAccount
        )
    }
}