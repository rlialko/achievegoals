package com.ruslanlialko.achievegoals.data.source.remote

import com.ruslanlialko.achievegoals.data.Goal
import com.ruslanlialko.achievegoals.data.Result


interface RemoteGoalsDataSource {

    suspend fun getGoals(): Result<List<Goal>>
}