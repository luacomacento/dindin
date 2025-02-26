package dev.luaoctaviano.dindin.core.domain

import dev.luaoctaviano.dindin.core.data.enums.TransactionType
import dev.luaoctaviano.dindin.core.data.repository.CategoryRepository
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetGroupedCategoryListUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    suspend operator fun invoke(): Flow<Map<TransactionType, List<Category>>> {
        val categoryList = categoryRepository.getCategories()

        return flowOf(categoryList.groupBy { it.type })
    }
}