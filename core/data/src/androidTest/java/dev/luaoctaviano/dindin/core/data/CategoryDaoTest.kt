package dev.luaoctaviano.dindin.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.luaoctaviano.dindin.core.data.source.local.DinDinDatabase
import dev.luaoctaviano.dindin.core.data.source.local.dao.CategoryDao
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CategoryDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: DinDinDatabase
    private lateinit var categoryDao: CategoryDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DinDinDatabase::class.java
        ).allowMainThreadQueries().build()

        categoryDao = database.categoryDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetAllCategories() = runBlocking {
        val category = Category(
            id = 1,
            name = "Alimentação",
            icon = "IC_CATEGORY_FOOD",
            type = TransactionType.EXPENSE
        )
        categoryDao.insert(category)

        val categories = categoryDao.getAll()
        assertEquals(1, categories.size)
        assertEquals(category, categories.first())
    }

    @Test
    fun countCategories() = runBlocking {
        val category = Category(
            id = 1,
            name = "Alimentação",
            icon = "IC_CATEGORY_FOOD",
            type = TransactionType.EXPENSE
        )
        categoryDao.insert(category)

        val count = categoryDao.count()
        assertEquals(1, count)
    }
}
