package dev.luaoctaviano.dindin.feature.transactions.newtransaction.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import dev.luaoctaviano.dindin.core.data.source.local.entity.Category
import dev.luaoctaviano.dindin.core.ui.composable.SelectionBottomSheet
import dev.luaoctaviano.dindin.core.ui.extension.CoreIcons
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.feature.transactions.extension.TransactionStrings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCategoryBottomSheet(
    categorySheetState: SheetState,
    categoryList: List<Category>?,
    onSelectCategory: (Category) -> Unit,
    onShowBottomSheet: (Boolean) -> Unit,
    onCleanCategory: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    SelectionBottomSheet(
        sheetState = categorySheetState,
        title = stringResource(TransactionStrings.action_select_category),
        onDismissRequest = {
            coroutineScope
                .launch {
                    categorySheetState.hide()
                }.invokeOnCompletion {
                    onShowBottomSheet(false)
                }
        },
        onClean = onCleanCategory,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.large),
        ) {
            categoryList?.let { list ->
                list.forEach {
                    val icon = CoreIcons.getIconByIdOrNull(it.icon)

                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(Dimens.large))
                                .clickable {
                                    onSelectCategory.invoke(it)
                                },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Dimens.large),
                    ) {
                        icon?.let { iconRes ->
                            Icon(
                                painter = painterResource(iconRes),
                                contentDescription = null,
                                tint = Color.Unspecified,
                            )
                        }

                        Text(
                            text = it.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}
