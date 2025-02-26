package dev.luaoctaviano.dindin.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.compose.ExtendedColors
import com.example.compose.LocalExtendedColors
import com.example.compose.backgroundDark
import com.example.compose.backgroundLight
import com.example.compose.errorContainerDark
import com.example.compose.errorContainerLight
import com.example.compose.errorDark
import com.example.compose.errorLight
import com.example.compose.expenseRedContainerDark
import com.example.compose.expenseRedContainerLight
import com.example.compose.expenseRedDark
import com.example.compose.expenseRedLight
import com.example.compose.homeBackgroundGradientEndDark
import com.example.compose.homeBackgroundGradientEndLight
import com.example.compose.homeBackgroundGradientStartDark
import com.example.compose.homeBackgroundGradientStartLight
import com.example.compose.incomeGreenContainerDark
import com.example.compose.incomeGreenContainerLight
import com.example.compose.incomeGreenDark
import com.example.compose.incomeGreenLight
import com.example.compose.inverseOnSurfaceDark
import com.example.compose.inverseOnSurfaceLight
import com.example.compose.inversePrimaryDark
import com.example.compose.inversePrimaryLight
import com.example.compose.inverseSurfaceDark
import com.example.compose.inverseSurfaceLight
import com.example.compose.onBackgroundDark
import com.example.compose.onBackgroundLight
import com.example.compose.onErrorContainerDark
import com.example.compose.onErrorContainerLight
import com.example.compose.onErrorDark
import com.example.compose.onErrorLight
import com.example.compose.onPrimaryContainerDark
import com.example.compose.onPrimaryContainerLight
import com.example.compose.onPrimaryDark
import com.example.compose.onPrimaryLight
import com.example.compose.onSecondaryContainerDark
import com.example.compose.onSecondaryContainerLight
import com.example.compose.onSecondaryDark
import com.example.compose.onSecondaryLight
import com.example.compose.onSurfaceDark
import com.example.compose.onSurfaceLight
import com.example.compose.onSurfaceVariantDark
import com.example.compose.onSurfaceVariantLight
import com.example.compose.onTertiaryContainerDark
import com.example.compose.onTertiaryContainerLight
import com.example.compose.onTertiaryDark
import com.example.compose.onTertiaryLight
import com.example.compose.outlineDark
import com.example.compose.outlineLight
import com.example.compose.outlineVariantDark
import com.example.compose.outlineVariantLight
import com.example.compose.primaryContainerDark
import com.example.compose.primaryContainerLight
import com.example.compose.primaryDark
import com.example.compose.primaryLight
import com.example.compose.scrimDark
import com.example.compose.scrimLight
import com.example.compose.secondaryContainerDark
import com.example.compose.secondaryContainerLight
import com.example.compose.secondaryDark
import com.example.compose.secondaryLight
import com.example.compose.surfaceBrightDark
import com.example.compose.surfaceBrightLight
import com.example.compose.surfaceContainerDark
import com.example.compose.surfaceContainerHighDark
import com.example.compose.surfaceContainerHighLight
import com.example.compose.surfaceContainerHighestDark
import com.example.compose.surfaceContainerHighestLight
import com.example.compose.surfaceContainerLight
import com.example.compose.surfaceContainerLowDark
import com.example.compose.surfaceContainerLowLight
import com.example.compose.surfaceContainerLowestDark
import com.example.compose.surfaceContainerLowestLight
import com.example.compose.surfaceDark
import com.example.compose.surfaceDimDark
import com.example.compose.surfaceDimLight
import com.example.compose.surfaceLight
import com.example.compose.surfaceVariantDark
import com.example.compose.surfaceVariantLight
import com.example.compose.tertiaryContainerDark
import com.example.compose.tertiaryContainerLight
import com.example.compose.tertiaryDark
import com.example.compose.tertiaryLight
import com.example.compose.transferBlueContainerDark
import com.example.compose.transferBlueContainerLight
import com.example.compose.transferBlueDark
import com.example.compose.transferBlueLight
import dev.luaoctaviano.dindin.core.ui.extension.resetStatusBarIconColor

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val lightExtendedColors = ExtendedColors(
    expenseRed = expenseRedLight,
    expenseRedContainer = expenseRedContainerLight,
    incomeGreen = incomeGreenLight,
    incomeGreenContainer = incomeGreenContainerLight,
    transferBlue = transferBlueLight,
    transferBlueContainer = transferBlueContainerLight,
    homeBackgroundGradientStart = homeBackgroundGradientStartLight,
    homeBackgroundGradientEnd = homeBackgroundGradientEndLight,
)

private val darkExtendedColors = ExtendedColors(
    expenseRed = expenseRedDark,
    expenseRedContainer = expenseRedContainerDark,
    incomeGreen = incomeGreenDark,
    incomeGreenContainer = incomeGreenContainerDark,
    transferBlue = transferBlueDark,
    transferBlueContainer = transferBlueContainerDark,
    homeBackgroundGradientStart = homeBackgroundGradientStartDark,
    homeBackgroundGradientEnd = homeBackgroundGradientEndDark,
)

@Composable
fun DinDinTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colorScheme: ColorScheme
    val extendedColors: ExtendedColors

    if (darkTheme) {
        colorScheme = darkScheme
        extendedColors = darkExtendedColors
    } else {
        colorScheme = lightScheme
        extendedColors = lightExtendedColors
    }

    resetStatusBarIconColor()

    CompositionLocalProvider(
        LocalExtendedColors provides extendedColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = DinDinTypography,
            content = content
        )
    }
}

object DinDinTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}
