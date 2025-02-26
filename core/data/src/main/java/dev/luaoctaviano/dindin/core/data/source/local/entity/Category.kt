package dev.luaoctaviano.dindin.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.luaoctaviano.dindin.core.data.enums.TransactionType

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val icon: String,
    val type: TransactionType,
    val isDefault: Boolean = false,
)
