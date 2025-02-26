package dev.luaoctaviano.dindin.core.data.repository

import dev.luaoctaviano.dindin.core.data.source.local.dao.DetailedTransaction
import dev.luaoctaviano.dindin.core.data.source.local.dao.TransactionDao
import dev.luaoctaviano.dindin.core.data.source.local.entity.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DefaultTransactionRepository @Inject constructor(
    private val localDataSource: TransactionDao,
) : TransactionRepository {
    override suspend fun getAllTransactions(): Flow<List<DetailedTransaction>> =
        localDataSource.getAllAsFlow()

    override suspend fun getLastFiveTransactions(): Flow<List<DetailedTransaction>> =
        localDataSource.getLastFiveAsFlow()

    override suspend fun insertTransaction(transaction: Transaction) =
        localDataSource.insert(transaction)

    override suspend fun deleteTransaction(transactionId: Long) {
        localDataSource.delete(transactionId)
    }

    override suspend fun getTotalBalance(dateInMillis: Long) =
        localDataSource.getTotalBalance(dateInMillis)
}