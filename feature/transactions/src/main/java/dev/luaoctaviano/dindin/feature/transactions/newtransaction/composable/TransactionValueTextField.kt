package dev.luaoctaviano.dindin.feature.transactions.newtransaction.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import dev.luaoctaviano.dindin.core.ui.extension.NumberCommaTransformation
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import dev.luaoctaviano.dindin.core.util.constant.IntConstants
import dev.luaoctaviano.dindin.core.util.extension.asCurrency

@Composable
fun TransactionValueTextField(
    price: String,
    textAlign: TextAlign = TextAlign.Right,
    textColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val rightAlign = textAlign in listOf(TextAlign.Right, TextAlign.End)

    TextField(
        singleLine = true,
        colors =
            TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                cursorColor = textColor,
                unfocusedPlaceholderColor = textColor.copy(0.7F),
                focusedPlaceholderColor = textColor,
            ),
        textStyle =
            MaterialTheme.typography.headlineLarge.copy(
                textAlign = textAlign,
                textDirection = TextDirection.Ltr
            ),
        shape = RectangleShape,
        modifier = Modifier.padding(vertical = Dimens.none).fillMaxWidth(),
        value = price,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = IntConstants.ZERO.toLong().asCurrency(),
                style =
                    MaterialTheme.typography.headlineLarge.copy(
                        textAlign =
                            if (rightAlign) {
                                TextAlign.End
                            } else {
                                TextAlign.Start
                            },
                    ),
            )
        },
        keyboardOptions =
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus(true) }),
        visualTransformation = NumberCommaTransformation(),
    )
}
