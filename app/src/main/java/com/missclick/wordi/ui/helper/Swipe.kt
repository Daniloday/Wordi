package com.missclick.wordi.ui.helper

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.absoluteValue

fun Modifier.swipe(directionCallback: (Direction) -> Unit): Modifier = composed {

    var direction by remember {
        mutableStateOf(Direction.DOWN)
    }

    pointerInput(Unit) {
        detectDragGestures(

            onDrag = { change, dragAmount ->
                change.consume()
                val (x, y) = dragAmount
                if (abs(x) > abs(y)) {
                    when {
                        x > 0 -> {
                            //right
                            direction = Direction.RIGHT

                        }

                        x < 0 -> {
                            // left
                            direction = Direction.LEFT

                        }
                    }
                } else {
                    when {
                        y > 0 -> {
                            // down
                            direction = Direction.DOWN

                        }

                        y < 0 -> {
                            // up
                            direction = Direction.UP

                        }
                    }
                }

            },
            onDragEnd = {
                directionCallback.invoke(direction)
            }
        )

    }


}

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

