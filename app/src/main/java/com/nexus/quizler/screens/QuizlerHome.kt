package com.nexus.quizler.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nexus.quizler.components.Questions

@Composable
fun QuizlerHome(viewModel: QuestionViewModel = hiltViewModel()) {
    Questions(viewModel)
}
