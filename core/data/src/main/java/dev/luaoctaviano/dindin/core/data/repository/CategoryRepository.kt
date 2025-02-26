package dev.luaoctaviano.dindin.core.data.repository

import dev.luaoctaviano.dindin.core.data.source.local.entity.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}