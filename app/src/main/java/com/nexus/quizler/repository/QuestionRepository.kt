package com.nexus.quizler.repository

import android.util.Log
import com.nexus.quizler.data.DataOrException
import com.nexus.quizler.model.Question
import com.nexus.quizler.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api: QuestionAPI) {

    private val dataOrException = DataOrException<ArrayList<Question>, Boolean, Exception>()

    suspend fun getQuestions(): DataOrException<ArrayList<Question>, Boolean, Exception> {

        try {
            dataOrException.loading = true
            dataOrException.data = api.getQuestions()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        } catch (e: Exception) {
            dataOrException.e = e
            e.localizedMessage?.let { Log.e("${QuestionRepository::class}", it) }
        }

        return dataOrException
    }

}
