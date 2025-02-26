package dev.luaoctaviano.dindin.core.data.repository

import dev.luaoctaviano.dindin.core.data.source.local.dao.CategoryDao
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DefaultCategoryRepository @Inject constructor(
    private val localDataSource: CategoryDao,
) : CategoryRepository {

    override suspend fun getCategories(): List<Category> =
        localDataSource.getAll()
}
