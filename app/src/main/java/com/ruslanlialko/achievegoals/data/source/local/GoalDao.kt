package com.ruslanlialko.achievegoals.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ruslanlialko.achievegoals.data.Goal

@Dao
interface GoalDao {

    @Query("SELECT * from goal_table ORDER BY id ASC")
    suspend fun getGoals(): List<Goal>

    @Insert
    suspend fun insert(goal: Goal)

    @Insert
    suspend fun insertAll(entities: List<Goal>)

    @Query("DELETE FROM goal_table")
    suspend fun deleteAll()
}