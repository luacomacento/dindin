package dev.luaoctaviano.dindin.feature.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.ui.extension.CoreDrawables
import dev.luaoctaviano.dindin.core.ui.extension.CoreIcons
import dev.luaoctaviano.dindin.core.ui.extension.getCurrencyTextColor
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.core.util.extension.asCurrency
import dev.luaoctaviano.dindin.feature.home.HomeStrings

fun LazyListScope.HomeAccountsSection(
    accounts: List<BankAccount>?,
    hideBalance: Boolean,
    onViewAccountsClick: () -> Unit,
) {
    item {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimens.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(HomeStrings.accounts_label),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
//            ViewAllButton(onViewAccountsClick)
        }
    }
    accounts?.let { accountsList ->
        items(accountsList.size) { index ->
            AccountInfo(
                accountsList[index],
                hideBalance,
            )
            Spacer(Modifier.size(Dimens.large))
        }
    }
}

@Composable
fun AccountInfo(
    account: BankAccount,
    hideBalance: Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.medium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val icon = CoreIcons.getIconByIdOrNull(account.iconId)

        icon?.let {
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(it),
                contentDescription = account.name,
                tint = Color.Unspecified,
            )
        }
        Column(
            modifier = Modifier.weight(1F),
        ) {
            Text(
                text = account.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = account.initialBalance.asCurrency(hideBalance),
                style = MaterialTheme.typography.labelLarge,
                color = account.initialBalance.getCurrencyTextColor(hideBalance),
            )
        }
        Icon(
            painter = painterResource(CoreDrawables.ic_chevron_right),
            tint = MaterialTheme.colorScheme.onSurface.copy(0.5F),
            contentDescription = null,
        )
    }
}

