package dev.luaoctaviano.dindin.core.data

import dev.luaoctaviano.dindin.core.data.repository.DefaultBankAccountRepository
import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountAtDate
import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountDao
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
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

class DefaultBankAccountRepositoryTest {
    private val localDataSource: BankAccountDao = mockk()
    private lateinit var repository: DefaultBankAccountRepository

    @Before
    fun setUp() {
        repository = DefaultBankAccountRepository(localDataSource)
    }

    @Test
    fun `getAccountById should return the correct bank account`() = runTest {
        val bankAccount = BankAccount(
            id = 1,
            name = "Carteira",
            initialBalance = 1000L,
            isDefault = true,
            iconId = "IC_WALLET"
        )
        coEvery { localDataSource.getById(1) } returns bankAccount

        val result = repository.getAccountById(1)

        assertEquals(bankAccount, result)
        coVerify { localDataSource.getById(1) }
    }

    @Test
    fun `getAccounts should return flow of bank accounts`() = runTest {
        val bankAccounts = listOf(
            BankAccount(
                id = 1,
                name = "Carteira",
                initialBalance = 1000L,
                isDefault = true,
                iconId = "IC_WALLET"
            ),
            BankAccount(
                id = 2,
                name = "Conta bancária",
                initialBalance = 5000L,
                isDefault = false,
                iconId = "IC_BANK_ACCOUNT"
            )
        )
        every { localDataSource.getAllAsFlow() } returns flowOf(bankAccounts)

        val result = repository.getAccounts()

        assertEquals(bankAccounts, result.last())
        verify { localDataSource.getAllAsFlow() }
    }

    @Test
    fun `getAccountsAtDate should return flow of bank accounts at date`() = runTest {
        val bankAccountsAtDate = listOf(
            BankAccountAtDate(
                id = 1,
                name = "Carteira",
                icon = "IC_WALLET",
                initialBalance = 1000L,
                currentBalance = 1200L
            )
        )
        val dateInMillis = Date().toInstant().toEpochMilli()

        every { localDataSource.getAllAtDate(dateInMillis) } returns flowOf(bankAccountsAtDate)

        val result = repository.getAccountsAtDate(dateInMillis)

        assertEquals(bankAccountsAtDate, result.last())
        verify { localDataSource.getAllAtDate(dateInMillis) }
    }

    @Test
    fun `clearDefault should call localDataSource clearDefault`() = runTest {
        coEvery { localDataSource.clearDefault() } returns 1

        repository.clearDefault()

        coVerify { localDataSource.clearDefault() }
    }

    @Test
    fun `update should call localDataSource update`() = runTest {
        val bankAccount = BankAccount(
            id = 1,
            name = "Updated Account",
            initialBalance = 1500L,
            isDefault = false,
            iconId = "icon3"
        )

        coEvery { localDataSource.update(bankAccount) } returns 1

        repository.update(bankAccount)

        coVerify { localDataSource.update(bankAccount) }
    }
}
