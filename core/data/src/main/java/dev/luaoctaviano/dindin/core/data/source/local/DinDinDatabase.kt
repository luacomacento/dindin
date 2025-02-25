package dev.luaoctaviano.dindin.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountDao
import dev.luaoctaviano.dindin.core.data.source.local.dao.CategoryDao
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category

@Database(entities = [BankAccount::class, Category::class], version = 1)
abstract class DinDinDatabase : RoomDatabase() {
    abstract fun bankAccountDao(): BankAccountDao
    abstract fun categoryDao(): CategoryDao
}
