package dev.luaoctaviano.dindin.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BankAccount(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val initialBalance: Long = 0L,
    val isDefault: Boolean = false,
    val iconId: String?,
)
