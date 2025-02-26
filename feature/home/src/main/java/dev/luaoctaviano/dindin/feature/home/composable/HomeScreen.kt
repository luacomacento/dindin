package dev.luaoctaviano.dindin.feature.home.composable

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import dev.luaoctaviano.dindin.core.ui.LocalNavControllerProvider
import dev.luaoctaviano.dindin.core.ui.extension.AppRoute
import dev.luaoctaviano.dindin.core.ui.extension.CoreStrings
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import dev.luaoctaviano.dindin.core.ui.extension.StatusBarIconColor
import dev.luaoctaviano.dindin.core.ui.extension.forceStatusBarIconColor
import dev.luaoctaviano.dindin.core.ui.extension.getHomeBackgroundGradientBrush
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.core.ui.theme.DinDinTheme
import dev.luaoctaviano.dindin.core.util.extension.asCurrency
import dev.luaoctaviano.dindin.feature.home.DummyHomeListener
import dev.luaoctaviano.dindin.feature.home.HomeHeaderUiState
import dev.luaoctaviano.dindin.feature.home.HomeListener
import dev.luaoctaviano.dindin.feature.home.HomeStrings
import dev.luaoctaviano.dindin.feature.home.HomeUiState
import dev.luaoctaviano.dindin.feature.home.HomeViewModel
import dev.luaoctaviano.dindin.feature.home.getHomeHeaderArrow
import dev.luaoctaviano.dindin.feature.home.getVisibilityIcon

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()
    val navController = LocalNavControllerProvider.current

    HomeScreenContent(
        modifier = modifier,
        uiState = uiState,
        listener = object : HomeListener {
            override fun onHideValuesClick() {
                viewModel.toggleHiddenValues()
            }

            override fun onDeleteTransactionClick(transactionId: Long) {
                viewModel.deleteTransaction(transactionId)
            }

            override fun onViewAlLTransactionsClick() {
                navController?.navigate(AppRoute.TRANSACTIONS.name)
            }
        }
    )
}

@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    listener: HomeListener,
    modifier: Modifier = Modifier,
) {
    forceStatusBarIconColor(StatusBarIconColor.LIGHT)

    Box(
        Modifier
            .fillMaxSize()
            .background(brush = getHomeBackgroundGradientBrush())
            .then(modifier)
    ) {
        HomeHeader(
            modifier = Modifier,
            uiState = uiState.header,
            hideValues = uiState.hideValues,
            listener = listener,
        )

        LazyColumn(
            modifier = Modifier
                .padding(top = 230.dp)
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(topStart = Dimens.huge, topEnd = Dimens.huge)
                )
                .clip(RoundedCornerShape(topStart = Dimens.huge, topEnd = Dimens.huge)),
            contentPadding = PaddingValues(Dimens.extraLarge)
        ) {
            HomeAccountsSection(
                accounts = uiState.bankAccounts,
                hideBalance = uiState.hideValues,
            )

            item {
                Spacer(modifier = Modifier.size(Dimens.medium))
            }

            HomeLastTransactionsSection(
                transactions = uiState.lastFiveTransactions,
                hideValues = uiState.hideValues,
                listener = listener,
            )
        }
    }
}

@Composable
internal fun HomeHeader(
    uiState: HomeHeaderUiState,
    listener: HomeListener,
    modifier: Modifier = Modifier,
    hideValues: Boolean = false,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = Dimens.extraLarge,
                end = Dimens.extraLarge,
                bottom = Dimens.extraLarge,
                top = Dimens.large
            ),
        verticalArrangement = Arrangement.spacedBy(Dimens.small)
    ) {
        Text(
            text = stringResource(HomeStrings.current_balance),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = uiState.totalBalance.asCurrency(hidden = hideValues),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
            IconButton(
                onClick = { listener.onHideValuesClick() }
            ) {
                Icon(
                    painter = painterResource(getVisibilityIcon(hidden = hideValues)),
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
        Spacer(Modifier.size(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.large),
        ) {
            HeaderCard(
                type = TransactionType.INCOME,
                description = stringResource(HomeStrings.income_this_month),
                balance = uiState.monthIncome,
                hideBalance = hideValues,
            )
            HeaderCard(
                type = TransactionType.EXPENSE,
                description = stringResource(HomeStrings.expenses_this_month),
                balance = uiState.monthExpense,
                hideBalance = hideValues,
            )
        }
    }
}

@Composable
internal fun RowScope.HeaderCard(
    type: TransactionType,
    description: String,
    balance: Long = 0L,
    hideBalance: Boolean,
) {
    Card(
        modifier = Modifier
            .weight(1F)
            .height(78.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(
                alpha = 0.08F
            ),
            contentColor = Color.White,
        )
    ) {
        Column(
            modifier =
                Modifier.padding(
                    top = Dimens.large,
                    bottom = Dimens.large,
                    end = Dimens.large,
                ),
        ) {
            Row(
                modifier = Modifier.padding(start = Dimens.large),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(description, style = MaterialTheme.typography.labelSmall)
            }
            Spacer(Modifier.size(Dimens.small))
            Row(
                modifier = Modifier.padding(start = Dimens.medium),
                horizontalArrangement = Arrangement.spacedBy(Dimens.tiny),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                type.getHomeHeaderArrow()?.let {
                    Icon(
                        modifier = Modifier.size(Dimens.large),
                        painter = painterResource(it),
                        contentDescription = null,
                    )
                }

                // Disabled for initial deploy
                //                var balanceText = balance.asCurrency(
                //                    hidden = hideBalance,
                //                )

                Text(
                    text = stringResource(CoreStrings.unavailable_label),
                    style =
                        MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                        ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenContentPreview(
    @PreviewParameter(HomePreviewParameterProvider::class)
    uiState: HomeUiState,
) {
    DinDinTheme {
        HomeScreenContent(
            uiState = uiState,
            listener = DummyHomeListener,
        )
    }
}