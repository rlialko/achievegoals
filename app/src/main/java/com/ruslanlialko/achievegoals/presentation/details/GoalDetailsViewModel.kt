package com.ruslanlialko.achievegoals.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruslanlialko.achievegoals.data.Goal
import com.ruslanlialko.achievegoals.data.repository.GoalsRepository

class GoalDetailsViewModel(repo: GoalsRepository) : ViewModel() {

    private val _goal = MutableLiveData<Goal>()
    val goal: LiveData<Goal> = _goal

    private val _currentSteps = MutableLiveData<Int>()
    val currentSteps: LiveData<Int> = _currentSteps

    private val _currentDistance = MutableLiveData<Int>()
    val currentDistance: LiveData<Int> = _currentDistance

    init {
        _currentSteps.value = 0
        _currentDistance.value = 0
    }

    fun setGoal(store: Goal) {
        _goal.value = store
    }

}
