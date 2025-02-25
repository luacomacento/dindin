package dev.luaoctaviano.dindin.core.data.source.local.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.luaoctaviano.dindin.core.data.repository.BankAccountRepository
import dev.luaoctaviano.dindin.core.data.repository.DefaultBankAccountRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindBankAccountRepository(repository: DefaultBankAccountRepository): BankAccountRepository
}