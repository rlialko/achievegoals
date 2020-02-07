package com.ruslanlialko.achievegoals.data.source.local

import com.ruslanlialko.achievegoals.data.Goal
import com.ruslanlialko.achievegoals.data.Result

interface LocalGoalsDataSource {

    suspend fun getGoals(): Result<List<Goal>>

    suspend fun saveGoals(goals: List<Goal>)

    suspend fun deleteAllGoals()
}