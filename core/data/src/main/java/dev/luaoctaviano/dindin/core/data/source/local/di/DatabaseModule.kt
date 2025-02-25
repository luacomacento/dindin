package dev.luaoctaviano.dindin.core.data.source.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.luaoctaviano.dindin.core.data.source.local.DinDinDatabase
import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountDao
import dev.luaoctaviano.dindin.core.data.source.local.dao.CategoryDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): DinDinDatabase {
        return Room
            .databaseBuilder(
                context.applicationContext,
                DinDinDatabase::class.java,
                "dindin.db"
            )
            .createFromAsset("database/initial_database.db")
            .build()
    }

    @Provides
    fun provideBankAccountDao(database: DinDinDatabase): BankAccountDao = database.bankAccountDao()

    @Provides
    fun provideCategoryDao(database: DinDinDatabase): CategoryDao = database.categoryDao()
}