package dev.luaoctaviano.dindin.core.ui.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import dev.luaoctaviano.dindin.core.ui.theme.DinDinTheme

@Composable
fun getHomeBackgroundGradientBrush() = Brush.linearGradient(
    // Previous colors kept for reference.
    //    0.6F to Color(0xFF1D2E85),
    //    1F to Color(0xFFCB106C),
    0.6F to DinDinTheme.colors.homeBackgroundGradientStart,
    1F to DinDinTheme.colors.homeBackgroundGradientEnd,
    start = Offset(0f, Float.POSITIVE_INFINITY),
    end = Offset(Float.POSITIVE_INFINITY, 200f)
)