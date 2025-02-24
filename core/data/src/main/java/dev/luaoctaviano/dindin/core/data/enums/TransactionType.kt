package dev.luaoctaviano.dindin.core.data.enums

enum class TransactionType(val id: Int) {
    EXPENSE(1),
    INCOME(2),
    TRANSFER(3);

    companion object {
        fun getByIdOrNUll(id: Int?) = TransactionType.entries.find { it.id == id }
    }
}