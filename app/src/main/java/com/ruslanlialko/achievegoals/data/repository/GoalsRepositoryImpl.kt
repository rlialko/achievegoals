package com.ruslanlialko.achievegoals.data.repository

import android.util.Log
import com.ruslanlialko.achievegoals.data.Goal
import com.ruslanlialko.achievegoals.data.Result
import com.ruslanlialko.achievegoals.data.Result.Error
import com.ruslanlialko.achievegoals.data.Result.Success
import com.ruslanlialko.achievegoals.data.source.local.LocalGoalsDataSource
import com.ruslanlialko.achievegoals.data.source.remote.RemoteGoalsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class GoalsRepositoryImpl(
    private val remoteGoalsDataSource: RemoteGoalsDataSource,
    private val localGoalsDataSource: LocalGoalsDataSource
) : GoalsRepository {

    override suspend fun getGoals(forceUpdate: Boolean): Result<List<Goal>> =
        withContext(Dispatchers.IO) {
            return@withContext fetchGoalsRemoteOrLocal(forceUpdate)
        }

    private suspend fun fetchGoalsRemoteOrLocal(forceUpdate: Boolean): Result<List<Goal>> {
        when (val remoteGoals = remoteGoalsDataSource.getGoals()) {
            is Error -> Log.d("Repository", "Remote goals loading failed")
            is Success -> {
                refreshLocalDataSource(remoteGoals.data)
                return remoteGoals
            }
        }

        val localGoals = localGoalsDataSource.getGoals()

        if (localGoals is Success) {
            return if (forceUpdate) {
                Error(Exception("No connection"), localGoals.data)
            } else {
                localGoals
            }
        }
        return Error(Exception("Error fetching"))
    }

    private suspend fun refreshLocalDataSource(goals: List<Goal>) {
        localGoalsDataSource.deleteAllGoals()
        localGoalsDataSource.saveGoals(goals)
    }
}