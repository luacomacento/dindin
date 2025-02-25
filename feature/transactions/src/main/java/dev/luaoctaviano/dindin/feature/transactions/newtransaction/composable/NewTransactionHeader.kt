package dev.luaoctaviano.dindin.feature.transactions.newtransaction.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.luaoctaviano.dindin.core.data.enums.TransactionType
import dev.luaoctaviano.dindin.core.ui.extension.CoreDrawables
import dev.luaoctaviano.dindin.core.ui.extension.CoreStrings
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.feature.transactions.extension.getScreenTitleString
import dev.luaoctaviano.dindin.feature.transactions.newtransaction.NewTransactionListener

@Composable
fun NewTransactionHeader(
    transactionValue: String,
    transactionType: TransactionType,
    listener: NewTransactionListener,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.tiny),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(
                contentPadding =
                    PaddingValues(
                        start = Dimens.tiny,
                        top = ButtonDefaults.ContentPadding.calculateTopPadding(),
                        end = Dimens.medium,
                        bottom = ButtonDefaults.ContentPadding.calculateBottomPadding(),
                    ),
                colors =
                    ButtonDefaults.textButtonColors().copy(
                        contentColor = Color.White,
                    ),
                onClick = { listener.onBackClick() }
            ) {
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .rotate(180f),
                    painter = painterResource(CoreDrawables.ic_chevron_right),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.size(Dimens.tiny))
                Text(text = stringResource(CoreStrings.action_cancel))
            }
        }

        Text(
            modifier =
                Modifier
                    .padding(start = Dimens.extraLarge, end = Dimens.extraLarge)
                    .fillMaxWidth(),
            text = stringResource(transactionType.getScreenTitleString()),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            textAlign = TextAlign.End,
        )

        TransactionValueTextField(
            price = transactionValue,
            onValueChange = { listener.onChangeTransactionValue(it) },
            textColor = Color.White,
        )

        TransactionTypeSelector(
            modifier = Modifier.padding(bottom = Dimens.small),
            selectedTransactionType = transactionType,
            onChangeTransactionType = { listener.onChangeTransactionType(it) },
            indicatorColor = Color.White,
        )
    }
}