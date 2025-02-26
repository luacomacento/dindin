package dev.luaoctaviano.dindin.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import java.util.Date

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val description: String,
    val amount: Long,
    val accountId: Long,
    val categoryId: Long,
    val type: TransactionType,
    val createdAt: Date = Date(),
    val dueDate: Date = Date(),
    val isPaid: Boolean,
)
