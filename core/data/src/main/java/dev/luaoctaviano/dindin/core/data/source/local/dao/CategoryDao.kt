package dev.luaoctaviano.dindin.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAllAsFlow(): Flow<List<Category>>

    @Query("SELECT count(*) FROM category")
    suspend fun count(): Int

    @Insert
    suspend fun insert(item: Category)
}