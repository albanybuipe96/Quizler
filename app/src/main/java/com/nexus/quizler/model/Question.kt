package com.nexus.quizler.model

data class Question(
    val answer: String,
    val category: String,
    val choices: List<String>,
    val question: String
)