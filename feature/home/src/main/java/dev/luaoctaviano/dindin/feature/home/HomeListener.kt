package dev.luaoctaviano.dindin.feature.home

import dev.luaoctaviano.dindin.core.ui.TransactionItemListener

interface HomeListener : TransactionItemListener {
    fun onHideValuesClick()
    fun onViewAlLTransactionsClick()
    override fun onDeleteTransactionClick(transactionId: Long)
}

object DummyHomeListener : HomeListener {
    override fun onHideValuesClick() = Unit
    override fun onViewAlLTransactionsClick() = Unit
    override fun onDeleteTransactionClick(transactionId: Long) = Unit
}