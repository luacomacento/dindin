package dev.luaoctaviano.dindin.core.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.luaoctaviano.dindin.core.ui.extension.CoreStrings
import dev.luaoctaviano.dindin.core.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionBottomSheet(
    sheetState: SheetState,
    title: String? = null,
    onDismissRequest: () -> Unit,
    onClean: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        contentWindowInsets = { WindowInsets(Dimens.none) },
        sheetState = sheetState,
    ) {
        Column(
            modifier =
                Modifier.windowInsetsPadding(
                    WindowInsets.navigationBars.only(
                        WindowInsetsSides.Bottom,
                    ),
                ).padding(Dimens.large),
            verticalArrangement = Arrangement.spacedBy(Dimens.large),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                title?.let {
                    Text(it, style = MaterialTheme.typography.titleLarge)
                }
                onClean?.let {
                    TextButton(
                        onClick = onClean,
                    ) {
                        Text(stringResource(CoreStrings.action_clear))
                    }
                }
            }
            content()
        }
    }
}
