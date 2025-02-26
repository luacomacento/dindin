package dev.luaoctaviano.dindin.core.domain

import dev.luaoctaviano.dindin.core.data.repository.TransactionRepository
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category
import dev.luaoctaviano.dindin.core.data.source.local.entity.Transaction
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import dev.luaoctaviano.dindin.core.util.extension.toTypedCurrencyLong
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Date

class CreateTransactionUseCaseTest {

    private lateinit var transactionRepository: TransactionRepository
    private lateinit var createTransactionUseCase: CreateTransactionUseCase

    @Before
    fun setUp() {
        transactionRepository = mockk(relaxed = true)
        createTransactionUseCase = CreateTransactionUseCase(transactionRepository)
    }


    @Test
    fun `should create transaction with description from category name when description is empty`() = runTest {
        val accountId = 1L
        val category = Category(id = 1L, name = "Alimentação", icon = "IC_CATEGORY_FOOD", type = TransactionType.EXPENSE)
        val amount = "100.00"
        val type = TransactionType.EXPENSE
        val isPaid = true

        val transactionSlot = slot<Transaction>()

        createTransactionUseCase.invoke("", amount, accountId, category, type, isPaid)

        coVerify { transactionRepository.insertTransaction(capture(transactionSlot)) }

        with(transactionSlot.captured) {
            assertEquals(category.name, this.description)
            assertEquals(-10000L, this.amount)
            assertEquals(accountId, this.accountId)
            assertEquals(category.id, this.categoryId)
            assertEquals(type, this.type)
            assertEquals(isPaid, this.isPaid)
        }
    }

    @Test
    fun `should create transaction with provided description when it is not empty`() = runBlocking {
        val accountId = 1L
        val description = "iFood"
        val category = Category(id = 1L, name = "Alimentação", icon = "IC_CATEGORY_FOOD", type = TransactionType.EXPENSE)
        val amount = "100.00"
        val type = TransactionType.EXPENSE
        val isPaid = true

        val transactionSlot = slot<Transaction>()

        createTransactionUseCase.invoke(description, amount, accountId, category, type, isPaid)

        coVerify { transactionRepository.insertTransaction(capture(transactionSlot)) }

        with(transactionSlot.captured) {
            assertEquals(description, this.description)
            assertEquals(-10000L, this.amount)
            assertEquals(accountId, this.accountId)
            assertEquals(category.id, this.categoryId)
            assertEquals(type, this.type)
            assertEquals(isPaid, this.isPaid)
        }
    }
}