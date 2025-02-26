package dev.luaoctaviano.dindin.feature.transactions.newtransaction

import dev.luaoctaviano.dindin.core.data.enums.TransactionType
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.ui.BaseScreenListener

interface NewTransactionListener : BaseScreenListener {
    fun onChangeTransactionValue(newValue: String)
    fun onChangeTransactionType(newType: TransactionType)
    fun onChangeTransactionDescription(newValue: String)
    fun onChangeTransactionPaidStatus(isPaid: Boolean)
    fun onSetDefaultAccountClick(account: BankAccount?)
    fun onToggleAccountSelector(open: Boolean)
    fun onToggleCategorySelector(open: Boolean)
}

internal object DummyNewTransactionListener : NewTransactionListener {
    override fun onChangeTransactionValue(newValue: String) = Unit

    override fun onChangeTransactionType(newType: TransactionType) = Unit

    override fun onChangeTransactionDescription(newValue: String) = Unit

    override fun onChangeTransactionPaidStatus(isPaid: Boolean) = Unit

    override fun onSetDefaultAccountClick(account: BankAccount?) = Unit

    override fun onToggleAccountSelector(open: Boolean) = Unit

    override fun onToggleCategorySelector(open: Boolean) = Unit

    override fun onBackClick() = Unit

}