package dev.luaoctaviano.dindin.feature.transactions.newtransaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import dev.luaoctaviano.dindin.core.data.repository.BankAccountRepository
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category
import dev.luaoctaviano.dindin.core.domain.CreateTransactionUseCase
import dev.luaoctaviano.dindin.core.domain.GetGroupedCategoryListUseCase
import dev.luaoctaviano.dindin.core.domain.SetDefaultBankAccountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTransactionViewModel @Inject constructor(
    private val setDefaultBankAccountUseCase: SetDefaultBankAccountUseCase,
    private val getGroupedCategoryListUseCase: GetGroupedCategoryListUseCase,
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val bankAccountRepository: BankAccountRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<NewTransactionUiState> = MutableStateFlow(NewTransactionUiState())

    val uiState: StateFlow<NewTransactionUiState>
        get() = _uiState.stateIn(viewModelScope, SharingStarted.Eagerly, NewTransactionUiState())

    private var fullCategoryList: List<Category> = emptyList()

    init {
        getAccounts()
        getCategories()
    }

    private fun getAccounts() {
        viewModelScope.launch {
            bankAccountRepository.getAccounts().collect { accountList ->
                _uiState.value = _uiState.value.copy(
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
                val selectedCategoryList =
                    groupedCategories[_uiState.value.transactionType].orEmpty()

                _uiState.value = _uiState.value.copy(
                    categoryList = selectedCategoryList,
                    category = selectedCategoryList.find { it.isDefault }
                )
            }
        }
    }

    fun changeTransactionValueField(newValue: String) {
        _uiState.value = _uiState.value.copy(
            amount = newValue
        )
    }

    fun changeTransactionType(newType: TransactionType) {
        val newCategoryList = fullCategoryList.filter { it.type == newType }

        _uiState.value = _uiState.value.copy(
            transactionType = newType,
            categoryList = newCategoryList,
            category = newCategoryList.find { it.isDefault }
        )
    }

    fun changeTransactionDescription(newValue: String) {
        _uiState.value = _uiState.value.copy(
            description = newValue
        )
    }

    fun changeTransactionPaidStatus(isPaid: Boolean) {
        _uiState.value = _uiState.value.copy(
            isPaid = isPaid
        )
    }

    fun setDefaultAccount(bankAccount: BankAccount) {
        viewModelScope.launch {
            val updatedAccount = setDefaultBankAccountUseCase(bankAccount)
            _uiState.value = _uiState.value.copy(
                associatedAccount = updatedAccount
            )
        }
    }

    fun changeAssociatedAccount(bankAccount: BankAccount?) {
        _uiState.value = _uiState.value.copy(
            associatedAccount = bankAccount
        )
    }

    fun changeCategory(category: Category?) {
        _uiState.value = _uiState.value.copy(
            category = category
        )
    }

    fun createTransaction() {
        viewModelScope.launch {
            if (_uiState.value.associatedAccount == null || _uiState.value.category == null) return@launch

            createTransactionUseCase(
                description = _uiState.value.description,
                amount = _uiState.value.amount,
                accountId = _uiState.value.associatedAccount?.id ?: 0L,
                category = _uiState.value.category,
                type = _uiState.value.transactionType,
                isPaid = _uiState.value.isPaid,
            )
        }
    }
}