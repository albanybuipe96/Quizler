package com.nexus.quizler.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.nexus.quizler.screens.QuestionViewModel

@Composable
fun Questions(viewModel: QuestionViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()
    val questionIndex = remember {
        mutableStateOf(0)
    }
    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator()
    } else {
        val question = try {
            questions?.get(questionIndex.value)
        } catch (e: Exception) {
            null
        }
        QuestionPanel(
            question = question!!,
            questionIndex = questionIndex,
            viewModel = viewModel,
            onNext = {
//                if (questionIndex.value <= viewModel.getQuestionCount())
                questionIndex.value = questionIndex.value + 1
            },
            onPrevious = {
//                if (questionIndex.value > 0)
                questionIndex.value = questionIndex.value - 1
            },
        )
    }
}