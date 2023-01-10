package com.github.Mindinventory.circularslider

import android.graphics.Paint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.Mindinventory.circularslider.clock_arms.models.ClockLineType
import com.github.Mindinventory.circularslider.ui.*
import kotlin.math.*

/**
 * A Circular slider for Tracking progress
 *
 * @param maxNum to provide maximum number for the slider
 * @param radiusCircle radius of the circular slider
 * @param percentageFontSize font size of the percentage text
 * @param percentageColor color of the percentage text
 * @param progressWidth width of the Progress
 * @param animDuration to set duration for the sliding animation
 * @param animDelay to set delay for the sliding animation
 * @param strokeCap to set strokes of the ends
 * @param thumbRadius to set the radius of the thumb
 * @param tickColor to set the color of the minute-like clock arms
 * @param tickhighlightedColor to set the color of the hour-like clock arms
 * @param dialColor  dial color
 * @param progressColor color of the progress bar
 * @param startThumbCircleColor Initial thumb color
 * @param thumbColor Thumb color
 * @param trackColor track color
 * @param trackOpacity Opacity of the track
 * @param trackWidth width of the track
 * @param isDisabled Flag to set enabled/disabled circular slider
 * @param staticProgress Static progress in case if isDisabled is true
 */
@Composable
fun CircularProgressBar(
    maxNum: Int = 50,
    radiusCircle: Dp = 150.dp,
    percentageFontSize: TextUnit = 28.sp,
    percentageColor: Color = DeepBlue,
    progressWidth: Float = 28f,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    strokeCap: StrokeCap = StrokeCap.Round,
    thumbRadius: Float = 40f,
    tickColor: Color = SkyBlue,
    tickhighlightedColor: Color = TextWhite,
    dialColor: Color = DullPurple,
    progressColor: Brush = Brush.linearGradient(colors = listOf(SkyBlue, Color.White)),
    startThumbCircleColor: List<Color> = listOf(SkyBlue, SkyBlue),
    thumbColor: List<Color> = listOf(Color.White, Color.White),
    trackColor: Color = Color.Black,
    trackOpacity: Float = 0.10f,
    trackWidth: Float = 20f,
    isDisabled: Boolean = false,
    staticProgress: Float = 0f,
    currentProgressToBeReturned: (Float) -> Unit,  //Float type value to be returned..to it's parent composable using State hoisting - we're passing currentPercentage to the parent composable
    currentUpdatedValue: String = ""
) {
    var radius by remember {
        mutableStateOf(0f)
    }

    var shapeCenter by remember {   //Center of the shape
        mutableStateOf(Offset.Zero)
    }

    var handleCenter by remember {  //Current position
        mutableStateOf(Offset.Zero)
    }

    var angle by remember { //Thumb and progress moves based on this (0 - 360)
        mutableStateOf(0.0)
    }

    var animationDuration by remember {
        mutableStateOf(1000)
    }

    animationDuration = animDuration

    val animatedAngle by animateFloatAsState(
        targetValue = angle.toFloat(),
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animDelay
        )
    )

    var currentValue by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = animatedAngle) {
        currentValue = if (angle < 1.5) 0f else ((animatedAngle + 3f) * maxNum / 360)     // + 3f to show 100% in the slider
    }

    var currentPercentage by remember { mutableStateOf(0f) }
    if (isDisabled) {
        currentPercentage = staticProgress
        angle = (currentPercentage * 360f / 100f).toDouble()
    } else {
        currentPercentage = currentValue * 100 / maxNum
    }
    //Returning percentage value to the parent composable
    if (currentPercentage in 0.0..100.0) {
        currentProgressToBeReturned(currentPercentage)
    }

    //Progressbar
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(radiusCircle * 2f)
            .rotate(-90f)
    ) {
        Canvas(
            modifier = Modifier
                .size(radiusCircle * 2f)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        handleCenter += dragAmount
                        angle = getRotationAngle(handleCenter, shapeCenter)
                        change.consumeAllChanges()
                    }
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { offset ->
                            handleCenter = Offset.Zero + offset
                            angle = getRotationAngle(handleCenter, shapeCenter)
                        }
                    )
                }

        ) {
            shapeCenter = center
            radius = size.minDimension / 2

            val x = (shapeCenter.x + cos(Math.toRadians(angle)) * radius).toFloat()
            val y = (shapeCenter.y + sin(Math.toRadians(angle)) * radius).toFloat()

            handleCenter = Offset(x, y)

            //track
            drawCircle(
                color = trackColor.copy(alpha = trackOpacity),
                style = Stroke(trackWidth),
                radius = radius
            )

            //Clock arms
            drawContext.canvas.nativeCanvas.apply {
                drawCircle(
                    center.x,
                    center.y,
                    radiusCircle.toPx(),
                    Paint().apply {
                        color = android.graphics.Color.TRANSPARENT
                        style = Paint.Style.FILL
                        setShadowLayer(
                            70f,
                            0f,
                            0f,
                            android.graphics.Color.argb(50, 0, 0, 0)
                        )
                    }
                )
            }

            for (i in 0..20) {
                val lineType = when {
                    i % 5 == 0 -> ClockLineType.Hours
                    else -> ClockLineType.Minutes
                }
                val lineLength = when (lineType) {
                    ClockLineType.Minutes -> 8.dp.toPx()
                    ClockLineType.Hours -> 12.dp.toPx()
                }
                val lineColor = when (lineType) {
                    ClockLineType.Hours -> tickColor
                    ClockLineType.Minutes -> tickhighlightedColor
                }
                val angleInRad = i * (360f / 60f) * (PI.toFloat() / 20f)
                val lineStart = Offset(
                    x = (radiusCircle.toPx() - lineLength) * cos(angleInRad) + center.x,
                    y = (radiusCircle.toPx() - lineLength) * sin(angleInRad) + center.y
                )
                val lineEnd = Offset(
                    x = radiusCircle.toPx() * cos(angleInRad) + center.x,
                    y = radiusCircle.toPx() * sin(angleInRad) + center.y
                )
                drawLine(
                    color = lineColor,
                    start = lineStart,
                    end = lineEnd,
                    strokeWidth = if (lineType == ClockLineType.Hours) 2.dp.toPx() else 1.dp.toPx()
                )
            }

            //Progress
            if (angle.toFloat() >= 359) {
                angle = 0.0
            }

            drawArc(
                brush = progressColor,
                startAngle = 0f,
                sweepAngle = if (isDisabled) angle.toFloat() else animatedAngle,
                useCenter = false,
                style = Stroke(progressWidth)
            )

            //Dial circle - Purple
            drawCircle(
                color = dialColor,
                radius = (radiusCircle * 0.8f).toPx()
            )
            //Inner Dial circle - 1
            drawCircle(
                color = Color.White,
                radius = (radiusCircle * 0.6f).toPx(),
                blendMode = BlendMode.Screen
            )
            //Inner Dial boarder
            drawCircle(
                color = Color.Gray,
                radius = (radiusCircle * 0.5f).toPx(),
                style = Stroke(
                    width = 1f,
                )
            )
            //Initial thumb circle at (0,0) coordinate
            drawCircle(
                brush = Brush.radialGradient(
                    colors = startThumbCircleColor.onEach { it.copy(alpha = 0.10f) },
                    center = Offset(size.width, size.height / 2)
                ),
                center = Offset(size.width, size.height / 2),
                radius = thumbRadius + 5f
            )
            //Inner initial circle
            drawCircle(
                color = thumbColor[0],
                center = Offset(size.width, size.height / 2),
                radius = thumbRadius - 10f
            )
            //Thumb
            drawCircle(
                brush = Brush.radialGradient(colors = thumbColor, center = handleCenter),
                center = handleCenter,
                radius = thumbRadius
            )
            //Inner Thumb
            drawCircle(
                color = startThumbCircleColor[0],
                center = handleCenter,
                radius = thumbRadius - 10f
            )
            //Outer Shadow
            drawCircle(
                color = SkyBlue.copy(alpha = 0.20f),
                center = handleCenter,
                radius = thumbRadius + 20f
            )
            //Extra layer to increase touch area
            drawCircle(
                color = Color.Transparent,
                center = handleCenter,
                radius = thumbRadius + 40f
            )
        }
        /**
         * Extra canvas layer to intercept touch event
         */
        Canvas(modifier = Modifier
            .clip(CircleShape)
            .size((radiusCircle) * 1.5f)
            .clickable(
                enabled = false,
                onClick = {}
            )) {
            drawCircle(
                color = Color.Transparent,
                radius = (radiusCircle - 16.dp).toPx(),
                blendMode = BlendMode.Screen
            )
        }
        Text(
            text = if (currentUpdatedValue == "") "${currentPercentage.roundToInt()} %" else currentUpdatedValue ?: "-",
            color = percentageColor,
            fontSize = percentageFontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.rotate(90f)
        )
    }
}

private fun getRotationAngle(currentPosition: Offset, center: Offset): Double {
    val (dx, dy) = currentPosition - center
    val theta = atan2(dy, dx).toDouble()

    var angle = Math.toDegrees(theta)

    if (angle < 0) {
        angle += 360.0
    }
    return angle
}