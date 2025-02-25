package dev.luaoctaviano.dindin.core.ui.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import dev.luaoctaviano.dindin.core.ui.theme.Dimens

@Composable
fun DinTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    @StringRes placeholder: Int? = null,
    @DrawableRes leadingIcon: Int? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val focusManager = LocalFocusManager.current
    Column {
        BasicTextField(
            value = value,
            modifier =
                modifier
                    .defaultMinSize(
                        minWidth = TextFieldDefaults.MinWidth,
                        minHeight = TextFieldDefaults.MinHeight,
                    )
                    .fillMaxWidth(),
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            textStyle =
                textStyle.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
            //        cursorBrush = SolidColor(colors.cursorColor(isError).value),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions =
                KeyboardActions(
                    onDone = { focusManager.clearFocus(true) },
                ),
            interactionSource = interactionSource,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            decorationBox = @Composable { innerTextField ->
                val alphaAnimation by animateFloatAsState(
                    targetValue = if (value.isEmpty()) 0.8F else 0F,
                    animationSpec =
                        tween(
                            durationMillis = 100,
                            easing = LinearEasing,
                        ),
                )

                Row(
                    modifier =
                        Modifier
                            .clip(RoundedCornerShape(Dimens.small)),
                    //                        .padding(horizontal = Dimens.medium),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    leadingIcon?.let {
                        Icon(
                            painter = painterResource(it),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                        Spacer(Modifier.size(Dimens.medium))
                    }
                    Box {
                        if (value.isEmpty()) {
                            placeholder?.let {
                                Box(
                                    modifier = Modifier.alpha(alphaAnimation),
                                ) {
                                    Text(
                                        text = stringResource(it),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                }
                            }
                        }
                        innerTextField()
                    }
                }
            },
        )
    }
}
