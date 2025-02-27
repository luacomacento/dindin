package com.example.compose

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val primaryLight = Color(0xFF8D4959)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFFFFD9DF)
val onPrimaryContainerLight = Color(0xFF713342)
val secondaryLight = Color(0xFF75565C)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = Color(0xFFFFD9DF)
val onSecondaryContainerLight = Color(0xFF5B3F44)
val tertiaryLight = Color(0xFF7A5732)
val onTertiaryLight = Color(0xFFFFFFFF)
val tertiaryContainerLight = Color(0xFFFFDCBC)
val onTertiaryContainerLight = Color(0xFF5F401D)
val errorLight = Color(0xFFBA1A1A)
val onErrorLight = Color(0xFFFFFFFF)
val errorContainerLight = Color(0xFFFFDAD6)
val onErrorContainerLight = Color(0xFF93000A)
val backgroundLight = Color(0xFFFFF8F7)
val onBackgroundLight = Color(0xFF22191B)
val surfaceLight = Color(0xFFFFF8F7)
val onSurfaceLight = Color(0xFF22191B)
val surfaceVariantLight = Color(0xFFF3DDE0)
val onSurfaceVariantLight = Color(0xFF524345)
val outlineLight = Color(0xFF847375)
val outlineVariantLight = Color(0xFFD6C2C4)
val scrimLight = Color(0xFF000000)
val inverseSurfaceLight = Color(0xFF382E2F)
val inverseOnSurfaceLight = Color(0xFFFEEDEE)
val inversePrimaryLight = Color(0xFFFFB1C0)
val surfaceDimLight = Color(0xFFE7D6D8)
val surfaceBrightLight = Color(0xFFFFF8F7)
val surfaceContainerLowestLight = Color(0xFFFFFFFF)
val surfaceContainerLowLight = Color(0xFFFFF0F1)
val surfaceContainerLight = Color(0xFFFBEAEB)
val surfaceContainerHighLight = Color(0xFFF5E4E6)
val surfaceContainerHighestLight = Color(0xFFEFDEE0)

val primaryDark = Color(0xFFFFB1C0)
val onPrimaryDark = Color(0xFF551D2C)
val primaryContainerDark = Color(0xFF713342)
val onPrimaryContainerDark = Color(0xFFFFD9DF)
val secondaryDark = Color(0xFFE4BDC3)
val onSecondaryDark = Color(0xFF43292E)
val secondaryContainerDark = Color(0xFF5B3F44)
val onSecondaryContainerDark = Color(0xFFFFD9DF)
val tertiaryDark = Color(0xFFECBE91)
val onTertiaryDark = Color(0xFF462A08)
val tertiaryContainerDark = Color(0xFF5F401D)
val onTertiaryContainerDark = Color(0xFFFFDCBC)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val backgroundDark = Color(0xFF191113)
val onBackgroundDark = Color(0xFFEFDEE0)
val surfaceDark = Color(0xff100b0c)
val onSurfaceDark = Color(0xFFEFDEE0)
val surfaceVariantDark = Color(0xFF524345)
val onSurfaceVariantDark = Color(0xFFD6C2C4)
val outlineDark = Color(0xFF9F8C8F)
val outlineVariantDark = Color(0xFF524345)
val scrimDark = Color(0xFF000000)
val inverseSurfaceDark = Color(0xFFEFDEE0)
val inverseOnSurfaceDark = Color(0xFF382E2F)
val inversePrimaryDark = Color(0xFF8D4959)
val surfaceDimDark = Color(0xFF191113)
val surfaceBrightDark = Color(0xFF413738)
val surfaceContainerLowestDark = Color(0xFF140C0D)
val surfaceContainerLowDark = Color(0xFF22191B)
val surfaceContainerDark = Color(0xFF261D1F)
val surfaceContainerHighDark = Color(0xFF312829)
val surfaceContainerHighestDark = Color(0xFF3C3234)

// Extended colors
val incomeGreenLight = Color(0xff0d9d6f)
val incomeGreenContainerLight = Color(0xff047853)
val expenseRedLight = Color(0xfff64d55)
val expenseRedContainerLight = Color(0xffc5383e)
val transferBlueLight = Color(0xFF5F69D9)
val transferBlueContainerLight = Color(0xff353c97)
val homeBackgroundGradientStartLight = Color(0xFF2D3B85)
val homeBackgroundGradientEndLight = Color(0xffc82f7a)


val incomeGreenDark = Color(0xff0d9d6f)
val incomeGreenContainerDark = Color(0xff0c5742)
val expenseRedDark = Color(0xfff64d55)
val expenseRedContainerDark = Color(0xff801f25)
val transferBlueDark = Color(0xFF5F69D9)
val transferBlueContainerDark = Color(0xff222667)
val homeBackgroundGradientStartDark = Color(0xff1e275a)
val homeBackgroundGradientEndDark = Color(0xff852052)

@Immutable
data class ExtendedColors(
    val expenseRed: Color,
    val expenseRedContainer: Color,
    val incomeGreen: Color,
    val incomeGreenContainer: Color,
    val transferBlue: Color,
    val transferBlueContainer: Color,
    val homeBackgroundGradientStart: Color,
    val homeBackgroundGradientEnd: Color,
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        expenseRed = Color.Unspecified,
        expenseRedContainer = Color.Unspecified,
        incomeGreen = Color.Unspecified,
        incomeGreenContainer = Color.Unspecified,
        transferBlue = Color.Unspecified,
        transferBlueContainer = Color.Unspecified,
        homeBackgroundGradientStart = Color.Unspecified,
        homeBackgroundGradientEnd = Color.Unspecified,
    )
}