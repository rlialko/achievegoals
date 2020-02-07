package com.ruslanlialko.achievegoals.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruslanlialko.achievegoals.data.Goal
import com.ruslanlialko.achievegoals.data.repository.GoalsRepository

class GoalDetailsViewModel(repo: GoalsRepository) : ViewModel() {

    private val _goal = MutableLiveData<Goal>()
    val goal: LiveData<Goal> = _goal

    fun setGoal(store: Goal) {
        _goal.value = store
    }

}
