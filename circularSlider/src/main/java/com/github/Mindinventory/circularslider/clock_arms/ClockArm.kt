package com.github.Mindinventory.circularslider.clock_arms

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.github.Mindinventory.circularslider.clock_arms.models.ClockLineType
import com.github.Mindinventory.circularslider.clock_arms.models.Time
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ClockArm() {
    val radius = 120.dp
    val time by remember {
        mutableStateOf(Time())
    }

    Canvas(
        modifier = androidx.compose.ui.Modifier
            .size(radius * 2f),
        onDraw = {
            drawContext.canvas.nativeCanvas.apply {
                drawCircle(
                    center.x,
                    center.y,
                    radius.toPx(),
                    Paint().apply {
                        color = Color.WHITE
                        style = Paint.Style.FILL
                        setShadowLayer(
                            60f,
                            0f,
                            0f,
                            Color.argb(50, 0, 0, 0)
                        )
                    }
                )
            }
            // draw lines
            for (i in 0..59) {
                val lineType = when {
                    i % 5 == 0 -> ClockLineType.Hours
                    else -> ClockLineType.Minutes
                }
                val lineLength = when (lineType) {
                    ClockLineType.Minutes -> 15.dp.toPx()
                    ClockLineType.Hours -> 25.dp.toPx()
                }
                val lineColor = when (lineType) {
                    ClockLineType.Hours -> androidx.compose.ui.graphics.Color.Black
                    ClockLineType.Minutes -> androidx.compose.ui.graphics.Color.LightGray
                }
                val angleInRad = i * (360f / 60f) * (PI.toFloat() / 180f)
                val lineStart = Offset(
                    x = (radius.toPx() - lineLength) * cos(angleInRad) + center.x,
                    y = (radius.toPx() - lineLength) * sin(angleInRad) + center.y
                )
                val lineEnd = Offset(
                    x = radius.toPx() * cos(angleInRad) + center.x,
                    y = radius.toPx() * sin(angleInRad) + center.y
                )
                drawLine(
                    color = lineColor,
                    start = lineStart,
                    end = lineEnd,
                    strokeWidth = 1.dp.toPx()
                )
            }
/*            // draw clock arms
            rotate(
                time.hours.toFloat() * (360f / 12f)
            ) {
                drawLine(
                    color = androidx.compose.ui.graphics.Color.Black,
                    start = center,
                    end = Offset(
                        x = center.x,
                        y = 34.dp.toPx()
                    ),
                    strokeWidth = 3.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
            rotate(
                time.minutes.toFloat() * (360f / 60f)
            ) {
                drawLine(
                    color = androidx.compose.ui.graphics.Color.DarkGray,
                    start = center,
                    end = Offset(
                        x = center.x,
                        y = 30.dp.toPx()
                    ),
                    strokeWidth = 2.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }
            rotate(
                time.seconds.toFloat() * (360f / 60f)
            ) {
                drawLine(
                    color = androidx.compose.ui.graphics.Color.Green,
                    start = center,
                    end = Offset(
                        x = center.x,
                        y = 28.dp.toPx()
                    ),
                    strokeWidth = 1.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }*/
/*            drawCircle(
                color = androidx.compose.ui.graphics.Color.Green,
                radius = 4.dp.toPx(),
                center = center
            )*/
        }
    )

}