package dev.luaoctaviano.dindin.core.ui.extension

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.TabPosition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.dp
import dev.luaoctaviano.dindin.core.ui.enums.ButtonState

fun Modifier.buttonPushDownAnim(isEnabled: Boolean) = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(
        if (buttonState == ButtonState.Pressed) {
            0.9f
        } else 1f
    )

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .pointerInput(buttonState) {
            if (isEnabled) {
                awaitPointerEventScope {
                    buttonState = if (buttonState == ButtonState.Pressed) {
                        waitForUpOrCancellation()
                        ButtonState.Idle
                    } else {
                        awaitFirstDown(false)
                        ButtonState.Pressed
                    }
                }
            }
        }
}

fun Modifier.bottomTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }) {
    val currentTabWidth by animateDpAsState(
        targetValue = 6.dp,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = (currentTabPosition.left) + ((currentTabPosition.width / 2) - 3.dp),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
        .clip(CircleShape)
}
