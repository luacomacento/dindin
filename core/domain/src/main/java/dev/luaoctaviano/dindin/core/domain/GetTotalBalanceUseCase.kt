package dev.luaoctaviano.dindin.core.domain

import dev.luaoctaviano.dindin.core.data.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetTotalBalanceUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {
    suspend operator fun invoke(date: Date): Flow<Long> {
        val dateInMillis = date.toInstant().toEpochMilli()

        return transactionRepository.getTotalBalance(dateInMillis)
    }
}