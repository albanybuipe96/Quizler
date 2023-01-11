package com.nexus.quizler.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexus.quizler.data.DataOrException
import com.nexus.quizler.model.Question
import com.nexus.quizler.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val repository: QuestionRepository) : ViewModel() {
    val data: MutableState<DataOrException<ArrayList<Question>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        getQuestions()
    }

    private fun getQuestions() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getQuestions()
            if (data.value.data.toString().isNotEmpty()) data.value.loading = false
        }
    }

    fun getQuestionCount(): Int = data.value.data?.toMutableList()?.size!!

}