package dev.luaoctaviano.dindin.core.ui.extension

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun getHomeBackgroundGradientBrush() = Brush.linearGradient(
    0.6F to Color(0xFF1D2E85),
    1F to Color(0xFFCB106C),
    start = Offset(0f, Float.POSITIVE_INFINITY),
    end = Offset(Float.POSITIVE_INFINITY, 200f)
)