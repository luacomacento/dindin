package dev.luaoctaviano.dindin

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.luaoctaviano.dindin.core.ui.LocalNavControllerProvider
import dev.luaoctaviano.dindin.core.ui.extension.AppRoute
import dev.luaoctaviano.dindin.feature.home.HomeViewModel
import dev.luaoctaviano.dindin.feature.home.composable.HomeScreen
import dev.luaoctaviano.dindin.feature.transactions.list.TransactionListViewModel
import dev.luaoctaviano.dindin.feature.transactions.list.composable.TransactionListScreen
import dev.luaoctaviano.dindin.feature.transactions.newtransaction.composable.NewTransactionScreen
import dev.luaoctaviano.dindin.feature.transactions.newtransaction.NewTransactionViewModel

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalNavControllerProvider provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = AppRoute.HOME.name,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(500)
                )
            },
        ) {
            composable(route = AppRoute.HOME.name) {
                val viewModel = hiltViewModel<HomeViewModel>()

                HomeScreen(
                    viewModel = viewModel,
                    modifier = modifier,
                )
            }

            composable(route = AppRoute.NEW_TRANSACTION.name) {
                val viewModel = hiltViewModel<NewTransactionViewModel>()

                NewTransactionScreen(
                    viewModel = viewModel,
                    modifier = modifier,
                )
            }

            composable(route = AppRoute.TRANSACTIONS.name) {
                val viewModel = hiltViewModel<TransactionListViewModel>()

                TransactionListScreen(
                    viewModel = viewModel,
                    modifier = modifier,
                )
            }
        }
    }
}
