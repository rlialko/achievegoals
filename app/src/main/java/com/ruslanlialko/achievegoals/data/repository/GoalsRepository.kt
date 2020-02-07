package com.ruslanlialko.achievegoals.data.repository

import com.ruslanlialko.achievegoals.data.Goal
import com.ruslanlialko.achievegoals.data.Result


interface GoalsRepository {

    suspend fun getGoals(forceUpdate: Boolean = false): Result<List<Goal>>
}
