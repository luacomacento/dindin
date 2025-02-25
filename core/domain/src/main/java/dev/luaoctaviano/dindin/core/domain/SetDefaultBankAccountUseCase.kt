package dev.luaoctaviano.dindin.core.domain

import dev.luaoctaviano.dindin.core.data.repository.BankAccountRepository
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import javax.inject.Inject

class SetDefaultBankAccountUseCase @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
) {
    suspend operator fun invoke(bankAccount: BankAccount): BankAccount? {
        bankAccountRepository.clearDefault()

        val updatedRows = bankAccountRepository.update(
            bankAccount.copy(isDefault = true)
        )

        return if (updatedRows > 0) {
            bankAccountRepository.getAccountById(bankAccount.id)
        } else {
            bankAccount
        }
    }
}