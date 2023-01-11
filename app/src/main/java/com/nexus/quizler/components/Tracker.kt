package com.nexus.quizler.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexus.quizler.ui.color.QuizlerColor

/**
 * Tracks questions attempted over total questions pulled from api.
 */
@Preview
@Composable
fun Tracker(current: Int = 10, total: Int = 100) {
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = QuizlerColor.mLightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp
                )
            ) {
                append("Question $current/")
            }
            withStyle(
                style = SpanStyle(
                    color = QuizlerColor.mLightGray,
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp
                )
            ) {
                append("$total")
            }
        }
    }, modifier = Modifier.padding(20.dp))
}