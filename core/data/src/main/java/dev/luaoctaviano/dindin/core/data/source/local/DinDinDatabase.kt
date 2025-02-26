package dev.luaoctaviano.dindin.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountDao
import dev.luaoctaviano.dindin.core.data.source.local.dao.CategoryDao
import dev.luaoctaviano.dindin.core.data.source.local.dao.TransactionDao
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category
import dev.luaoctaviano.dindin.core.data.source.local.entity.Transaction

@Database(entities = [BankAccount::class, Category::class, Transaction::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class DinDinDatabase : RoomDatabase() {
    abstract fun bankAccountDao(): BankAccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
}
