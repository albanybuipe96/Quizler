package com.nexus.quizler.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexus.quizler.model.QuestionItem
import com.nexus.quizler.screens.QuestionViewModel
import com.nexus.quizler.ui.color.QuizlerColor

//@Preview
@Composable
fun QuestionPanel(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionViewModel,
    onNext: (Int) -> Unit = {},
    onPrevious: (Int) -> Unit = {},
) {
    val choices = remember(question) {
        question.choices.toMutableList()
    }
    val answer = remember(question) {
        mutableStateOf<Int?>(null)
    }
    val correctResponse = remember(question) {
        mutableStateOf<Boolean?>(null)
    }
    val updateResponse: (Int) -> Unit = remember(question) {
        {
            answer.value = it
            correctResponse.value = choices[it] == question.answer
        }
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = QuizlerColor.mDarkPurple
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            if (questionIndex.value >= 3)  ScoreBoard(score = questionIndex.value)
            Tracker(current = questionIndex.value + 1, viewModel.getQuestionCount())
            DottedSpacer()
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = question.question,
                    modifier = Modifier
                        .padding(6.dp)
                        .align(alignment = Alignment.Start)
                        .fillMaxHeight(0.3f),
                    color = QuizlerColor.mOffWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    lineHeight = 22.sp
                )
                choices.forEachIndexed { index, answerText ->
                    Row(
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxWidth()
                            .height(45.dp)
                            .border(
                                width = 4.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        QuizlerColor.mOffWhite,
                                        QuizlerColor.mDarkPurple
                                    )
                                ),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .clip(
                                RoundedCornerShape(
                                    topStartPercent = 50,
                                    topEndPercent = 50,
                                    bottomStartPercent = 50,
                                    bottomEndPercent = 50,
                                )
                            )
                            .background(Color.Transparent),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (answer.value == index),
                            onClick = { updateResponse(index) },
                            modifier = Modifier.padding(start = 16.dp),
                            colors = RadioButtonDefaults.colors(
                                selectedColor = if (correctResponse.value == true && index == answer.value) {
                                    Color.Green.copy(alpha = 0.2f)
                                } else {
                                    Color.Red.copy(alpha = 0.2f)
                                }
                            )
                        ) // end RadioButton

                        Text(text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Light,
                                    color = if (correctResponse.value == true && index == answer.value) {
                                        Color.Green
                                    } else {
                                        QuizlerColor.mOffWhite
                                    },
                                    fontSize = 17.sp
                                )
                            ) {
                                append(answerText)
                            }
                        }, modifier = Modifier.padding(6.dp))
                    }
                }
//                Spacer(modifier = Modifier.fillMaxHeight())
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(
                        onClick = { onPrevious(questionIndex.value) },
                        modifier = Modifier
                            .padding(3.dp),
//                            .align(alignment = Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(34.dp),
                        enabled = questionIndex.value > 0,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = QuizlerColor.mLightBlue,
                            disabledBackgroundColor = MaterialTheme.colors.onSurface
                                .copy(alpha = ContentAlpha.disabled),
                            disabledContentColor = QuizlerColor.mLightBlue.copy(alpha = 0.2f),
                        ),
                        content = {
                            Text(
                                text = "<<|",
                                modifier = Modifier.padding(4.dp),
                                color = QuizlerColor.mOffWhite,
                                fontSize = 17.sp
                            )
                        }
                    ) // end Button [previous]
                    Button(
                        onClick = { onNext(questionIndex.value) },
                        modifier = Modifier
                            .padding(3.dp),
//                            .align(alignment = Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(34.dp),
                        enabled = questionIndex.value <= viewModel.getQuestionCount(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = QuizlerColor.mLightBlue,
                            disabledBackgroundColor = MaterialTheme.colors.onSurface
                                .copy(alpha = ContentAlpha.disabled),
                            disabledContentColor = QuizlerColor.mLightBlue.copy(alpha = 0.2f),
                        ),
                        content = {
                            Text(
                                text = "|>>",
                                modifier = Modifier.padding(4.dp),
                                color = QuizlerColor.mOffWhite,
                                fontSize = 17.sp
                            )
                        }
                    ) // end Button [next]
                }

            }
        }
    }
}
