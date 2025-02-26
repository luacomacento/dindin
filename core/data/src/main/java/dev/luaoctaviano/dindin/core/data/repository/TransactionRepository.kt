package dev.luaoctaviano.dindin.core.data.repository

import dev.luaoctaviano.dindin.core.data.source.local.dao.DetailedTransaction
import dev.luaoctaviano.dindin.core.data.source.local.entity.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun getAllTransactions(): Flow<List<DetailedTransaction>>
    suspend fun getLastFiveTransactions(): Flow<List<DetailedTransaction>>
    suspend fun insertTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transactionId: Long)
    suspend fun getTotalBalance(dateInMillis: Long): Flow<Long>
}