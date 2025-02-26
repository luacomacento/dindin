package dev.luaoctaviano.dindin.core.ui.extension

import androidx.annotation.DrawableRes

enum class CoreIcons(
    val id: String,
    @DrawableRes
    val resource: Int
) {
    WALLET("IC_WALLET", CoreDrawables.ic_wallet),
    BANK_ACCOUNT("IC_BANK_ACCOUNT", CoreDrawables.ic_bank_account),
    CATEGORY_FOOD("IC_CATEGORY_FOOD", CoreDrawables.ic_category_food),
    CATEGORY_HEALTH("IC_CATEGORY_HEALTH", CoreDrawables.ic_category_health),
    CATEGORY_TRANSPORT("IC_CATEGORY_TRANSPORT", CoreDrawables.ic_category_transport),
    CATEGORY_SALARY("IC_CATEGORY_SALARY", CoreDrawables.ic_category_salary),
    CATEGORY_OTHER("IC_CATEGORY_OTHER", CoreDrawables.ic_category_other);

    companion object {
        fun getIconByIdOrNull(iconId: String?) = entries.find { it.id == iconId }?.resource
    }
}