package dev.luaoctaviano.dindin.core.data

import dev.luaoctaviano.dindin.core.data.repository.DefaultCategoryRepository
import dev.luaoctaviano.dindin.core.data.source.local.dao.CategoryDao
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DefaultCategoryRepositoryTest {
    private val localDataSource: CategoryDao = mockk()
    private lateinit var repository: DefaultCategoryRepository

    @Before
    fun setUp() {
        repository = DefaultCategoryRepository(localDataSource)
    }

    @Test
    fun `getCategories should return list from localDataSource`() = runTest {
        val categories = listOf(
            Category(id = 1, name = "Alimentação", icon = "IC_CATEGORY_FOOD", type = TransactionType.EXPENSE),
            Category(id = 2, name = "Saúde", icon = "IC_CATEGORY_HEALTH", type = TransactionType.INCOME)
        )

        coEvery { localDataSource.getAll() } returns categories

        val result = repository.getCategories()

        assertEquals(categories, result)
        coVerify { localDataSource.getAll() }
    }
}