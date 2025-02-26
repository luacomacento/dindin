package dev.luaoctaviano.dindin.feature.transactions.newtransaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.luaoctaviano.dindin.core.data.enums.TransactionType
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category
import dev.luaoctaviano.dindin.core.domain.GetAccountListAtDateUseCase
import dev.luaoctaviano.dindin.core.domain.GetGroupedCategoryListUseCase
import dev.luaoctaviano.dindin.core.domain.SetDefaultBankAccountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTransactionViewModel @Inject constructor(
    private val setDefaultBankAccountUseCase: SetDefaultBankAccountUseCase,
    private val getAccountListAtDateUseCase: GetAccountListAtDateUseCase,
    private val getGroupedCategoryListUseCase: GetGroupedCategoryListUseCase,
) : ViewModel() {

    val uiState: MutableStateFlow<NewTransactionUiState> = MutableStateFlow(NewTransactionUiState())

    private var fullCategoryList: List<Category> = emptyList()

    init {
        getAccounts()
        getCategories()
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

    private fun getCategories() {
        viewModelScope.launch {
            getGroupedCategoryListUseCase().collect { groupedCategories ->
                fullCategoryList = groupedCategories.values.flatten()
                val selectedCategoryList = groupedCategories[uiState.value.transactionType].orEmpty()

                uiState.value = uiState.value.copy(
                    categoryList = selectedCategoryList,
                    category = selectedCategoryList.find { it.isDefault }
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
        val newCategoryList = fullCategoryList.filter { it.type == newType }

        uiState.value = uiState.value.copy(
            transactionType = newType,
            categoryList = newCategoryList,
            category = newCategoryList.find { it.isDefault }
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

    fun changeCategory(category: Category?) {
        uiState.value = uiState.value.copy(
            category = category
        )
    }
}