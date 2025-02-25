package dev.luaoctaviano.dindin.core.ui.extension

import androidx.annotation.DrawableRes

enum class CoreIcons(
    val id: String,
    @DrawableRes
    val resource: Int
) {
    WALLET("IC_WALLET", CoreDrawables.ic_wallet);

    companion object {
        fun getIconByIdOrNull(iconId: String?) = entries.find { it.id == iconId }?.resource
    }
}