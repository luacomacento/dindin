package dev.luaoctaviano.dindin.core.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavControllerProvider =
    staticCompositionLocalOf<NavController?> { null }