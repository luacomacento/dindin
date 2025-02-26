package dev.luaoctaviano.dindin.core.domain

import dev.luaoctaviano.dindin.core.data.repository.BankAccountRepository
import dev.luaoctaviano.dindin.core.data.source.local.dao.BankAccountAtDate
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.util.Date
import java.util.TimeZone
import javax.inject.Inject

class GetAccountListAtDateUseCase @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
) {
    operator fun invoke(date: Date): Flow<List<BankAccountAtDate>> {
        val dateInMillis = date.toInstant().toEpochMilli()

        return bankAccountRepository.getAccountsAtDate(dateInMillis)
    }
}