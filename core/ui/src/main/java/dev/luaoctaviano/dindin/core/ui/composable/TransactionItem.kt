package dev.luaoctaviano.dindin.core.ui.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.luaoctaviano.dindin.core.data.source.local.dao.DetailedTransaction
import dev.luaoctaviano.dindin.core.ui.TransactionItemListener
import dev.luaoctaviano.dindin.core.ui.extension.CoreIcons
import dev.luaoctaviano.dindin.core.ui.extension.CoreStrings
import dev.luaoctaviano.dindin.core.ui.extension.getCurrencyColor
import dev.luaoctaviano.dindin.core.ui.extension.getIsPaidDescription
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.core.util.extension.asCurrency

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionItem(
    transaction: DetailedTransaction,
    hideValues: Boolean,
    listener: TransactionItemListener,
) {
    var expandedMenu by remember { mutableStateOf(false) }

    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = {},
                    onLongClick = { expandedMenu = !expandedMenu },
                ),
            horizontalArrangement = Arrangement.spacedBy(Dimens.medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val icon = CoreIcons.getIconByIdOrNull(transaction.categoryIcon)

            icon?.let {
                Icon(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(it),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
            Column(
                modifier = Modifier.weight(1F),
            ) {
                Text(
                    text = transaction.description,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = transaction.accountName,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = transaction.amount.asCurrency(hideValues),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = transaction.type.getCurrencyColor(hideValues),
                )
                Text(
                    text = stringResource(transaction.isPaid.getIsPaidDescription(transaction.type)),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        DropdownMenu(
            expanded = expandedMenu,
            onDismissRequest = { expandedMenu = false }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(CoreStrings.action_delete)) },
                onClick = {
                    listener.onDeleteTransactionClick(transaction.id)
                    expandedMenu = false
                }
            )
        }
    }
}
