package dev.luaoctaviano.dindin.core.data

import dev.luaoctaviano.dindin.core.data.repository.DefaultTransactionRepository
import dev.luaoctaviano.dindin.core.data.source.local.dao.DetailedTransaction
import dev.luaoctaviano.dindin.core.data.source.local.dao.TransactionDao
import dev.luaoctaviano.dindin.core.data.source.local.entity.Transaction
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Date


class DefaultTransactionRepositoryTest {

    private val localDataSource: TransactionDao = mockk()
    private lateinit var repository: DefaultTransactionRepository

    @Before
    fun setUp() {
        repository = DefaultTransactionRepository(localDataSource)
    }

    @Test
    fun `getAllTransactions should return flow of transactions`() = runTest {
        val transactions = listOf(
            DetailedTransaction(
                id = 1,
                description = "iFood",
                amount = 5000L,
                accountName = "Carteira",
                categoryName = "Alimentação",
                categoryIcon = "IC_CATEGORY_FOOD",
                type = TransactionType.EXPENSE,
                createdAt = Date(),
                dueDate = Date(),
                isPaid = true
            ),
            DetailedTransaction(
                id = 1,
                description = "Araújo",
                amount = 5000L,
                accountName = "Conta bancária",
                categoryName = "Saúde",
                categoryIcon = "IC_CATEGORY_HEALTH",
                type = TransactionType.EXPENSE,
                createdAt = Date(),
                dueDate = Date(),
                isPaid = true
            ),
        )

        every { localDataSource.getAllAsFlow() } returns flowOf(transactions)

        val result = repository.getAllTransactions()

        assertEquals(transactions, result.last())
        verify { localDataSource.getAllAsFlow() }
    }

    @Test
    fun `getLastFiveTransactions should return flow of last five transactions`() = runTest{
        val lastFiveTransactions = List(5) {
            DetailedTransaction(
                id = 1,
                description = "iFood",
                amount = 5000L,
                accountName = "Carteira",
                categoryName = "Alimentação",
                categoryIcon = "IC_CATEGORY_FOOD",
                type = TransactionType.EXPENSE,
                createdAt = Date(),
                dueDate = Date(),
                isPaid = true
            )
        }

        every { localDataSource.getLastFiveAsFlow() } returns flowOf(lastFiveTransactions)

        val result = repository.getLastFiveTransactions()

        assertEquals(lastFiveTransactions, result.last())
        assert(result.last().size == 5)
        verify { localDataSource.getLastFiveAsFlow() }
    }

    @Test
    fun `insertTransaction should call localDataSource insert`() = runTest {
        val transaction = Transaction(
            id = 1,
            description = "Salário",
            amount = 15000L,
            accountId = 1,
            categoryId = 2,
            type = TransactionType.INCOME,
            createdAt = Date(),
            dueDate = Date(),
            isPaid = false
        )

        coEvery { localDataSource.insert(transaction) } returns Unit

        repository.insertTransaction(transaction)

        coVerify { localDataSource.insert(transaction) }
    }

    @Test
    fun `deleteTransaction should call localDataSource delete`() = runTest {
        coEvery { localDataSource.delete(1) } returns Unit

        repository.deleteTransaction(1)

        coVerify { localDataSource.delete(1) }
    }

    @Test
    fun `getTotalBalance should return total balance for given date`() = runTest {
        val totalBalance = 50000L
        val dateInMillis = Date().toInstant().toEpochMilli()

        coEvery { localDataSource.getTotalBalance(dateInMillis) } returns flowOf(totalBalance)

        val result = repository.getTotalBalance(dateInMillis)

        assertEquals(totalBalance, result.last())
        coVerify { localDataSource.getTotalBalance(dateInMillis) }
    }
}
