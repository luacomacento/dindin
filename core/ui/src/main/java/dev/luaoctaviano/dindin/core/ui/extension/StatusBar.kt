package dev.luaoctaviano.dindin.core.ui.extension

import android.app.Activity
import android.content.res.Configuration
import android.view.View
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

enum class StatusBarIconColor {
    LIGHT,
    DARK,
}

@Composable
fun forceStatusBarIconColor(
    color: StatusBarIconColor,
    disposeToDefault: Boolean = true,
): Boolean {
    if (LocalInspectionMode.current) return true

    val view = LocalView.current
    val window = (view.context as Activity).window
    val isAppearanceLightStatusBars =
        when (color) {
            StatusBarIconColor.LIGHT -> false
            StatusBarIconColor.DARK -> true
        }
    val previousAppearanceLightStatusBars =
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars

    DisposableEffect(isSystemInDarkTheme()) {
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
            isAppearanceLightStatusBars

        onDispose {
            if (disposeToDefault) {
                setDefaultStatusBar(view)
            } else {
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                    previousAppearanceLightStatusBars
            }
        }
    }

    return isAppearanceLightStatusBars
}

@Composable
fun resetStatusBarIconColor(): Boolean {
    if (LocalInspectionMode.current) return false

    val view = LocalView.current

    DisposableEffect(
        isSystemInDarkTheme()
    ) {
        setDefaultStatusBar(view)
        onDispose { }
    }

    return true
}

private fun setDefaultStatusBar(view: View) {
    val window = (view.context as Activity).window
    val uiMode =
        window.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
        uiMode != Configuration.UI_MODE_NIGHT_YES
}
