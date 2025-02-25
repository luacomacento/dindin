package dev.luaoctaviano.dindin.core.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import dev.luaoctaviano.dindin.core.ui.composable.navigation.DinNavigationBar
import dev.luaoctaviano.dindin.core.ui.composable.navigation.NavBarListener
import dev.luaoctaviano.dindin.core.ui.extension.AppRoute
import dev.luaoctaviano.dindin.core.ui.theme.DinDinTheme

@Composable
fun MainScaffold(
    currentDestination: NavDestination?,
    navBarListener: NavBarListener,
    modifier: Modifier = Modifier,
    content: @Composable (contentPadding: PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            DinNavigationBar(
                currentDestination = currentDestination,
                listener = navBarListener,
            )
        }
    ) { contentPadding ->
        content(contentPadding)
    }
}

@Preview
@Composable
private fun MainScaffoldPreview() {
    DinDinTheme {
        MainScaffold(
            currentDestination = null,
            navBarListener = object : NavBarListener {
                override fun onItemClick(itemRoute: AppRoute) = Unit
            }
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

            }
        }
    }
}