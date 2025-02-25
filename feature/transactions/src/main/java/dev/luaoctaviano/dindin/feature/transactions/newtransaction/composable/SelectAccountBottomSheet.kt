package dev.luaoctaviano.dindin.feature.transactions.newtransaction.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import dev.luaoctaviano.dindin.core.data.source.local.entity.BankAccount
import dev.luaoctaviano.dindin.core.ui.composable.SelectionBottomSheet
import dev.luaoctaviano.dindin.core.ui.extension.CoreIcons
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.feature.transactions.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectAccountBottomSheet(
    accountSheetState: SheetState,
    accountList: List<BankAccount>?,
    onSelectAccount: (BankAccount) -> Unit,
    onToggleBottomSheet: (showBottomSheet: Boolean) -> Unit,
    onCleanAccount: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    SelectionBottomSheet(
        sheetState = accountSheetState,
        title = stringResource(R.string.action_select_account),
        onDismissRequest = {
            coroutineScope
                .launch {
                    accountSheetState.hide()
                }.invokeOnCompletion {
                    onToggleBottomSheet(false)
                }
        },
        onClean = onCleanAccount,
    ) {
        accountList?.let { list ->
            list.forEach { account ->
                val icon = CoreIcons.getIconByIdOrNull(account.iconId)

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(Dimens.large))
                            .clickable {
                                onSelectAccount.invoke(
                                    account,
                                )
                            },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Dimens.large),
                ) {
                    icon?.let {
                        Icon(
                            painter = painterResource(it),
                            tint = Color.Unspecified,
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = account.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}
