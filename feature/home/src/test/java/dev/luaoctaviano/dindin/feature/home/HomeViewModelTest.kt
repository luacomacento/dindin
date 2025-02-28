package dev.luaoctaviano.dindin.feature.home

import dev.luaoctaviano.dindin.core.data.repository.TransactionRepository
import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountAtDate
import dev.luaoctaviano.dindin.core.data.source.local.dao.DetailedTransaction
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.domain.GetAccountListAtDateUseCase
import dev.luaoctaviano.dindin.core.domain.GetTotalBalanceUseCase
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
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

    private val mockedBankAccount = BankAccountAtDate(
        id = 1L,
        name = "Carteira",
        icon = "IC_WALLET"
    )

    private val mockedTransaction = DetailedTransaction(
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

    @Before
    fun setup() {
        coEvery { getTotalBalanceUseCase(any()) } returns flowOf(1000L)
        coEvery { getAccountListAtDateUseCase(any()) } returns flowOf(
            listOf(
                mockedBankAccount
            )
        )
        coEvery { transactionRepository.getLastFiveTransactions() } returns flowOf(
            listOf(
                mockedTransaction,
                mockedTransaction.copy(id = 2)
            )
        )
        coEvery { transactionRepository.deleteTransaction(any()) } just Runs

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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `header should show total balance`() = runTest {
        var stateResult = HomeUiState()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect { stateResult = it }
        }

        advanceUntilIdle()

        assertEquals(1000L, stateResult.header.totalBalance)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `bankAccounts should show loaded bankAccount`() = runTest {
        var stateResult = HomeUiState()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect { stateResult = it }
        }

        advanceUntilIdle()

        assertEquals(listOf(mockedBankAccount), stateResult.bankAccounts)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `lastFiveTransactions should show loaded transaction list`() = runTest {
        var stateResult = HomeUiState()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect { stateResult = it }
        }

        advanceUntilIdle()

        assertEquals(2, stateResult.lastFiveTransactions.size)
        assertEquals(
            listOf(mockedTransaction, mockedTransaction.copy(id = 2)),
            stateResult.lastFiveTransactions
        )
    }

    @Test
    fun `toggleHiddenValues should toggle hideValue on uiState`() = runTest {
        assertEquals(false, viewModel.uiState.value.hideValues)

        viewModel.toggleHiddenValues()

        val newState = viewModel.uiState.first()

        assertEquals(true, newState.hideValues)
    }

    @Test
    fun `deleteTransaction should call repository with the provided id`() = runTest {
        val transactionId = 2L

        viewModel.deleteTransaction(transactionId)

        coVerify { transactionRepository.deleteTransaction(transactionId) }
    }
}