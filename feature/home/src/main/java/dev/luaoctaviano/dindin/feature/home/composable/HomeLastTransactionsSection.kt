package dev.luaoctaviano.dindin.feature.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.luaoctaviano.dindin.core.data.source.local.dao.DetailedTransaction
import dev.luaoctaviano.dindin.core.ui.composable.TransactionItem
import dev.luaoctaviano.dindin.core.ui.extension.CoreStrings
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.feature.home.HomeListener
import dev.luaoctaviano.dindin.feature.home.HomeStrings

fun LazyListScope.HomeLastTransactionsSection(
    transactions: List<DetailedTransaction>,
    hideValues: Boolean,
    listener: HomeListener,
) {
    item {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimens.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(HomeStrings.last_transactions_label),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )

            TextButton(
                onClick = { listener.onViewAlLTransactionsClick() }
            ) {
                Text(
                    text = stringResource(CoreStrings.action_view_all)
                )
            }
        }
    }

    items(transactions.size) { index ->
        TransactionItem(
            transaction = transactions[index],
            hideValues = hideValues,
            listener = listener,
        )

        Spacer(Modifier.size(Dimens.large))
    }
}
