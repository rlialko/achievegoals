package com.ruslanlialko.achievegoals.data.source.local

import com.ruslanlialko.achievegoals.data.Goal
import com.ruslanlialko.achievegoals.data.Result
import com.ruslanlialko.achievegoals.data.Result.Error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LocalGoalsDataSourceImpl(
    private val goalDao: GoalDao
) : LocalGoalsDataSource {

    override suspend fun getGoals(): Result<List<Goal>> = withContext(Dispatchers.IO) {
        return@withContext try {
            Result.Success(goalDao.getGoals())
        } catch (e: Exception) {
            Error<List<Goal>>(e)
        }
    }

    override suspend fun saveGoals(goals: List<Goal>) {
        goalDao.insertAll(goals)
    }

    override suspend fun deleteAllGoals() = withContext(Dispatchers.IO) {
        goalDao.deleteAll()
    }
}