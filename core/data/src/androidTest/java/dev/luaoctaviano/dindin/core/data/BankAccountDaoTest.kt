package dev.luaoctaviano.dindin.core.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.luaoctaviano.dindin.core.data.source.local.DinDinDatabase
import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountDao
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BankAccountDaoTest {

    private lateinit var database: DinDinDatabase
    private lateinit var bankAccountDao: BankAccountDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DinDinDatabase::class.java
        ).allowMainThreadQueries().build()

        bankAccountDao = database.bankAccountDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndRetrieveBankAccount() = runBlocking {
        val bankAccount = BankAccount(
            id = 1,
            name = "Carteira",
            iconId = "IC_WALLET",
            initialBalance = 10000
        )
        bankAccountDao.insert(bankAccount)

        val result = bankAccountDao.getById(1)
        assertEquals(bankAccount, result)
    }

    @Test
    fun updateBankAccount() = runBlocking {
        val bankAccount = BankAccount(
            id = 1,
            name = "Carteira",
            iconId = "IC_WALLET",
            initialBalance = 10000,
            isDefault = false
        )
        bankAccountDao.insert(bankAccount)

        val updatedAccount = bankAccount.copy(name = "Poupança", isDefault = true)
        val rowsUpdated = bankAccountDao.update(updatedAccount)

        val result = bankAccountDao.getById(1)
        assertEquals(1, rowsUpdated)
        assertEquals(updatedAccount, result)
    }

    @Test
    fun clearDefaultBankAccount() = runBlocking {
        val wallet = BankAccount(
            id = 1,
            name = "Carteira",
            iconId = "IC_WALLET",
            initialBalance = 10000,
            isDefault = true,
        )
        val bankAccount = BankAccount(
            id = 2,
            name = "Conta bancária",
            iconId = "IC_BANK_ACCOUNT",
            initialBalance = 20000,
            isDefault = false,
        )
        bankAccountDao.insert(wallet)
        bankAccountDao.insert(bankAccount)

        val clearedCount = bankAccountDao.clearDefault()
        val result = bankAccountDao.getById(1)

        assertEquals(1, clearedCount)
        assertEquals(false, result?.isDefault)
    }

    @Test
    fun deleteBankAccountById() = runBlocking {
        val bankAccount = BankAccount(
            id = 1,
            name = "To Delete",
            initialBalance = 200,
            isDefault = false,
            iconId = "icon_3"
        )
        bankAccountDao.insert(bankAccount)

        bankAccountDao.deleteById(1)

        val result = bankAccountDao.getById(1)

        assertEquals(null, result)
    }

    @Test
    fun countBankAccounts() = runBlocking {
        bankAccountDao.insert(
            BankAccount(
                id = 1,
                name = "Carteira",
                iconId = "IC_WALLET",
                initialBalance = 10000
            )
        )
        bankAccountDao.insert(
            BankAccount(
                id = 2,
                name = "Conta bancária",
                iconId = "IC_BANK_ACCOUNT",
                initialBalance = 20000
            )
        )

        val count = bankAccountDao.count()
        assertEquals(2, count)
    }

    @Test
    fun getAllBankAccountsAsFlow() = runBlocking {
        val wallet = BankAccount(
                id = 1,
                name = "Carteira",
                iconId = "IC_WALLET",
                initialBalance = 10000
            )
        val bankAccount = BankAccount(
            id = 2,
            name = "Conta bancária",
            iconId = "IC_BANK_ACCOUNT",
            initialBalance = 20000
        )
        bankAccountDao.insert(wallet)
        bankAccountDao.insert(bankAccount)

        val accounts = bankAccountDao.getAllAsFlow().first()
        assertEquals(listOf(wallet, bankAccount), accounts)
    }
}