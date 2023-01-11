package com.nexus.quizler.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nexus.quizler.ui.color.QuizlerColor

@Preview
@Composable
fun DottedSpacer(
    pathEffect: PathEffect = PathEffect.dashPathEffect(
        floatArrayOf(10f, 10f),
        phase = 0f,
    )
) {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp), onDraw = {
        drawLine(
            color = QuizlerColor.mLightGray,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = 0f),
            pathEffect = pathEffect
        )
    })
}