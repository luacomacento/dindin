package dev.luaoctaviano.dindin.core.domain

import dev.luaoctaviano.dindin.core.data.repository.BankAccountRepository
import javax.inject.Inject

class GetAccountListAtDateUseCase @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
) {
    operator fun invoke() =
        bankAccountRepository.getAccounts()
}