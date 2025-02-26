package dev.luaoctaviano.dindin.feature.transactions.newtransaction.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.luaoctaviano.dindin.core.util.enums.TransactionType
import dev.luaoctaviano.dindin.core.ui.extension.bottomTabIndicatorOffset
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.feature.transactions.extension.getDescription


@Composable
fun TransactionTypeSelector(
    selectedTransactionType: TransactionType,
    onChangeTransactionType: (TransactionType) -> Unit,
    indicatorColor: Color,
    modifier: Modifier = Modifier,
) {
    val selectedTabIndex =
        when (selectedTransactionType) {
            TransactionType.EXPENSE -> 0
            TransactionType.INCOME -> 1
            TransactionType.TRANSFER -> 2
        }
    Row(
        modifier = modifier.clip(RoundedCornerShape(Dimens.huge)),
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            divider = {},
            containerColor = Color.Transparent,
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        height = 6.dp,
                        modifier = Modifier.bottomTabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = indicatorColor,
                    )
                }
            },
        ) {
            TransactionType.entries.forEach {
                // Disabled for initial deploy
                if (it == TransactionType.TRANSFER) return@forEach

                TypeSelectorTab(
                    transactionType = it,
                    isSelected = selectedTransactionType == it,
                    onChangeTransactionType = onChangeTransactionType)
            }
        }
    }
}

@Composable
internal fun TypeSelectorTab(
    transactionType: TransactionType,
    isSelected: Boolean,
    onChangeTransactionType: (TransactionType) -> Unit,
) {
    Tab(
        selected = isSelected,
        onClick = { onChangeTransactionType(transactionType) },
        selectedContentColor = Color.White,
        unselectedContentColor = Color.White.copy(0.5F),
        text = {
            Text(
                text = stringResource(transactionType.getDescription()),
                fontSize = 12.sp,
            )
        },
        enabled = transactionType.isEnabled,
//        interactionSource = NoRippleInteractionSource(),
    )
}