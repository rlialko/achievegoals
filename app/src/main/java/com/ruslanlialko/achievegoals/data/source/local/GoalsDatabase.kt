package com.ruslanlialko.achievegoals.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ruslanlialko.achievegoals.data.Goal

@Database(entities = [Goal::class], version = 1, exportSchema = false)
abstract class GoalDatabase : RoomDatabase() {

    abstract fun getDao(): GoalDao
}