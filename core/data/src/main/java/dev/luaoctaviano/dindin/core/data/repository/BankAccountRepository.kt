package dev.luaoctaviano.dindin.core.data.repository

import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import kotlinx.coroutines.flow.Flow

interface BankAccountRepository {
    suspend fun getAccountById(id: Long): BankAccount?

    fun getAccounts(): Flow<List<BankAccount>>

    suspend fun insertAccount(account: BankAccount)

    suspend fun deleteAccount(id: Long)

    suspend fun clearDefault()

    suspend fun update(bankAccount: BankAccount): Int
}