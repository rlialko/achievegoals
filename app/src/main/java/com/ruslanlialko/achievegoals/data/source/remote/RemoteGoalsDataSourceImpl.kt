package com.ruslanlialko.achievegoals.data.source.remote

import com.ruslanlialko.achievegoals.data.Goal
import com.ruslanlialko.achievegoals.data.Result
import com.ruslanlialko.achievegoals.data.Result.Error
import com.ruslanlialko.achievegoals.data.Result.Success
import com.ruslanlialko.achievegoals.data.api.DataApiService
import com.ruslanlialko.achievegoals.data.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RemoteGoalsDataSourceImpl(
    private val service: DataApiService
) : RemoteGoalsDataSource {

    override suspend fun getGoals(): Result<List<Goal>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = service.getData()
            if (response.isSuccessful && response.body() != null) {
                return@withContext Success(response.body()!!.items.map { it.toEntity() })
            } else {
                return@withContext Error<List<Goal>>(Exception("Data is null"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Error<List<Goal>>(e)
        }
    }

}