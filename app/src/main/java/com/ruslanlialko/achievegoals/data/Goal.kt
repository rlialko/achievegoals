package com.ruslanlialko.achievegoals.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "goal_table")
data class Goal(
    @PrimaryKey @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "goal") var goal: Int,
    @ColumnInfo(name = "reward_trophy") var rewardTrophy: String,
    @ColumnInfo(name = "reward_points") var rewardPoints: Int
) : Parcelable

data class GoalsResponse(
    var items: List<GoalResponse>
)

data class GoalResponse(
    var id: String,
    var title: String,
    var description: String,
    var type: String,
    var goal: Int,
    var reward: RewardResponse
)

data class RewardResponse(
    var trophy: String,
    var points: Int
)

fun GoalResponse.toEntity(): Goal {
    return Goal(
        this.id,
        this.title,
        this.description,
        this.type,
        this.goal,
        this.reward.trophy,
        this.reward.points
    )
}