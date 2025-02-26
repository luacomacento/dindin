package dev.luaoctaviano.dindin.core.domain

import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import dev.luaoctaviano.dindin.core.data.repository.TransactionRepository
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category
import dev.luaoctaviano.dindin.core.data.source.local.entity.Transaction
import dev.luaoctaviano.dindin.core.util.extension.toTypedCurrencyLong
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {
    suspend operator fun invoke(
        description: String,
        amount: String,
        accountId: Long?,
        category: Category?,
        type: TransactionType,
        isPaid: Boolean
    ) {
        val newTransaction = Transaction(
            description = description.ifEmpty { category?.name.orEmpty() },
            amount = amount.toTypedCurrencyLong(type),
            accountId = accountId ?: 0L,
            categoryId = category?.id ?: 0L,
            type = type,
            isPaid = isPaid,
        )

        return transactionRepository.insertTransaction(newTransaction)
    }
}