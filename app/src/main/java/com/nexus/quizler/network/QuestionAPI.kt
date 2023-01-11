package com.nexus.quizler.network

import com.nexus.quizler.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionAPI {

    @GET("world.json")
    suspend fun getQuestions(): ArrayList<Question>

}