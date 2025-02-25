package dev.luaoctaviano.dindin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.luaoctaviano.dindin.core.ui.composable.MainScaffold
import dev.luaoctaviano.dindin.core.ui.composable.navigation.NavBarListener
import dev.luaoctaviano.dindin.core.ui.extension.AppRoute
import dev.luaoctaviano.dindin.core.ui.extension.StatusBarIconColor
import dev.luaoctaviano.dindin.core.ui.extension.forceStatusBarIconColor
import dev.luaoctaviano.dindin.core.ui.theme.DinDinTheme
import dev.luaoctaviano.dindin.feature.home.composable.HomeScreen
import dev.luaoctaviano.dindin.feature.home.HomeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
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
                    navBarListener = object : NavBarListener {
                        override fun onItemClick(itemRoute: AppRoute) {
                            if (itemRoute.name == currentDestination?.route) return

                            navController.navigate(itemRoute.name) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    }
                ) { contentPadding ->
                    MainNavHost(
                        modifier = Modifier.padding(contentPadding),
                        navController = navController,
                    )
                }
            }
        }
    }
}

