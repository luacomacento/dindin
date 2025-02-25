package dev.luaoctaviano.dindin.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.luaoctaviano.dindin.core.data.repository.BankAccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
) : ViewModel() {

    val uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())

    init {
        getAccounts()
    }

    private fun getAccounts() {
        viewModelScope.launch {
            bankAccountRepository.getAccounts().collect {
                uiState.value = uiState.value.copy(
                    bankAccounts = it
                )
            }
        }
    }
}