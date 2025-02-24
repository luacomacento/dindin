package dev.luaoctaviano.dindin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.luaoctaviano.dindin.core.ui.composable.MainScaffold
import dev.luaoctaviano.dindin.core.ui.theme.DinDinTheme
import dev.luaoctaviano.dindin.feature.home.HomePreviewParameterProvider
import dev.luaoctaviano.dindin.feature.home.HomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = currentBackStackEntry?.destination

            DinDinTheme {
                MainScaffold(
                    currentDestination = currentDestination,
                ) { contentPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(contentPadding),
                        uiState = HomePreviewParameterProvider().defaultState
                    )
                }
            }
        }
    }
}

