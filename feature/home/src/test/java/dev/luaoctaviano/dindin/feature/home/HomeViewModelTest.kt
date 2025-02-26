package dev.luaoctaviano.dindin.feature.home

import dev.luaoctaviano.dindin.core.data.repository.TransactionRepository
import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountAtDate
import dev.luaoctaviano.dindin.core.data.source.local.dao.DetailedTransaction
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.domain.GetAccountListAtDateUseCase
import dev.luaoctaviano.dindin.core.domain.GetTotalBalanceUseCase
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Date

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val getAccountListAtDateUseCase: GetAccountListAtDateUseCase = mockk()
    private val getTotalBalanceUseCase: GetTotalBalanceUseCase = mockk()
    private val transactionRepository: TransactionRepository = mockk()

    @Before
    fun setup() {
        coEvery { getTotalBalanceUseCase(any()) } returns flowOf(1000L)
        coEvery { getAccountListAtDateUseCase(any()) } returns flowOf(
            listOf(
                BankAccountAtDate(
                    id = 1L,
                    name = "Carteira",
                    icon = "IC_WALLET"
                )
            )
        )
        coEvery { transactionRepository.getLastFiveTransactions() } returns flowOf(
            listOf(
                DetailedTransaction(
                    id = 1L,
                    description = "iFood",
                    amount = 5000L,
                    accountName = "Carteira",
                    categoryName = "Alimentação",
                    categoryIcon = "IC_CATEGORY_FOOD",
                    type = TransactionType.EXPENSE,
                    createdAt = Date(),
                    dueDate = Date(),
                    isPaid = true,
                )
            )
        )

        viewModel = HomeViewModel(
            getAccountListAtDateUseCase,
            getTotalBalanceUseCase,
            transactionRepository
        )
    }

    @Test
    fun `should initialize with empty lists and zero balance`() = runTest {
        val expectedBalance = 0L
        val expectedAccounts = emptyList<BankAccount>()
        val expectedTransactions = emptyList<DetailedTransaction>()

        assertEquals(expectedBalance, viewModel.uiState.value.header.totalBalance)
        assertEquals(expectedAccounts, viewModel.uiState.value.bankAccounts)
        assertEquals(expectedTransactions, viewModel.uiState.value.lastFiveTransactions)
    }
}