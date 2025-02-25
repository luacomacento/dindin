package dev.luaoctaviano.dindin.core.ui.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.luaoctaviano.dindin.core.ui.extension.CoreDrawables
import dev.luaoctaviano.dindin.core.ui.theme.Dimens
import kotlinx.coroutines.launch

@Composable
fun DropDownSelector(
    @DrawableRes leadingIcon: Int,
    placeholder: String,
    @DrawableRes selectedIcon: Int?,
    selectedText: String?,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    trailingAction: @Composable () -> Unit = {},
    onOpen: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier =
            Modifier
                .clip(RoundedCornerShape(Dimens.small))
                .clickable {
                    coroutineScope.launch {
                        onOpen.invoke()
                    }
                }.fillMaxWidth()
                .height(56.dp),
        horizontalArrangement = Arrangement.spacedBy(Dimens.medium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(leadingIcon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )
        selectedText?.let { selectedString ->
            Box(
                modifier = Modifier.weight(1F),
            ) {
                Row(
                    modifier =
                        Modifier
                            .border(
                                1.dp,
                                borderColor,
                                shape = RoundedCornerShape(Dimens.large),
                            )
                            .padding(Dimens.small),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.small),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    selectedIcon?.let {
                        Icon(
                            modifier = Modifier.size(Dimens.huge),
                            painter = painterResource(it),
                            tint = Color.Unspecified,
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = selectedString,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Spacer(modifier = Modifier.size(Dimens.quark))
                }
            }
        } ?: run {
            Text(
                modifier = Modifier.alpha(0.8F).weight(1F),
                text = placeholder,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        trailingAction()
        Icon(
            modifier = Modifier.rotate(90F),
            painter = painterResource(CoreDrawables.ic_chevron_right),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}
