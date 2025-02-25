package dev.luaoctaviano.dindin.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import kotlinx.coroutines.flow.Flow

@Dao
interface BankAccountDao {
    @Query("SELECT * FROM BankAccount WHERE id = :id")
    suspend fun getById(id: Long): BankAccount?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BankAccount)

    @Update
    suspend fun update(item: BankAccount): Int

    @Query("UPDATE BankAccount SET isDefault = 0 WHERE isDefault = 1")
    suspend fun clearDefault(): Int

    @Query("DELETE FROM BankAccount WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT count(*) FROM BankAccount")
    suspend fun count(): Int

    @Query("SELECT * FROM BankAccount")
    fun getAllAsFlow(): Flow<List<BankAccount>>
}
