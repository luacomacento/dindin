package dev.luaoctaviano.dindin.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import dev.luaoctaviano.dindin.core.data.enums.TransactionType
import dev.luaoctaviano.dindin.core.ui.extension.StatusBarIconColor
import dev.luaoctaviano.dindin.core.ui.extension.forceStatusBarIconColor
import dev.luaoctaviano.dindin.core.ui.extension.getHomeBackgroundGradientBrush
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.core.ui.theme.DinDinTheme
import dev.luaoctaviano.dindin.core.util.extension.asCurrency

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
) {
    forceStatusBarIconColor(StatusBarIconColor.LIGHT)

    Box(
        Modifier
            .fillMaxSize()
            .background(brush = getHomeBackgroundGradientBrush())
    ) {
        HomeHeader(
            modifier = modifier,
            uiState = uiState.header,
            hideValues = uiState.hideValues,
        )

        Column(
            modifier = Modifier
                .padding(top = 244.dp)
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
        ) {
        }
    }
}

@Composable
internal fun HomeHeader(
    uiState: HomeHeaderUiState,
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
                onClick = {}
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
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .weight(1F)
            .height(78.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x12FFFFFF),
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

                var balanceText = balance.asCurrency(
                    hidden = hideBalance,
                )

//                BoxWithConstraints {
                Text(
                    text = balanceText,
                    style =
                        MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                        ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
//                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(HomePreviewParameterProvider::class)
    uiState: HomeUiState,
) {
    DinDinTheme {
        HomeScreen(
            uiState = uiState
        )
    }
}