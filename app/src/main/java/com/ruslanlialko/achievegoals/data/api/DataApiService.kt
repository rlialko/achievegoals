package com.ruslanlialko.achievegoals.data.api

import com.ruslanlialko.achievegoals.data.GoalsResponse
import retrofit2.Response
import retrofit2.http.GET

interface DataApiService {

    @GET("goals")
    suspend fun getData(): Response<GoalsResponse>
}