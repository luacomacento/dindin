package dev.luaoctaviano.dindin.core.data.repository

import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountAtDate
import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountDao
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultBankAccountRepository @Inject constructor(
    private val localDataSource: BankAccountDao,
) : BankAccountRepository {
    override suspend fun getAccountById(id: Long): BankAccount? =
        localDataSource.getById(id)

    override fun getAccounts(): Flow<List<BankAccount>> =
        localDataSource.getAllAsFlow()

    override fun getAccountsAtDate(dateInMillis: Long): Flow<List<BankAccountAtDate>> =
        localDataSource.getAllAtDate(dateInMillis)

    override suspend fun clearDefault() {
        localDataSource.clearDefault()
    }

    override suspend fun update(bankAccount: BankAccount) =
        localDataSource.update(bankAccount)
}
