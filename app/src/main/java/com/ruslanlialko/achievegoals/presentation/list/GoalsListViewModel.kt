package com.ruslanlialko.achievegoals.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruslanlialko.achievegoals.R
import com.ruslanlialko.achievegoals.data.Goal
import com.ruslanlialko.achievegoals.data.Result.Error
import com.ruslanlialko.achievegoals.data.Result.Success
import com.ruslanlialko.achievegoals.data.repository.GoalsRepository
import com.ruslanlialko.achievegoals.utils.toSingleEvent
import kotlinx.coroutines.launch

class GoalsListViewModel(private val goalsRepository: GoalsRepository) : ViewModel() {
    private val _items = MutableLiveData<List<Goal>>().apply { value = emptyList() }
    val items: LiveData<List<Goal>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _snackbarText = MutableLiveData<Int>()
    val snackbarText: LiveData<Int> = _snackbarText.toSingleEvent()

    private val _openGoalEvent = MutableLiveData<Goal>()
    val openGoalEvent: LiveData<Goal> = _openGoalEvent.toSingleEvent()

    init {
        loadGoals()
    }

    private fun loadGoals(forceUpdate: Boolean = false) {
        _dataLoading.value = true
        viewModelScope.launch {
            val goalsResult = goalsRepository.getGoals(forceUpdate)

            if (goalsResult is Success) {
                _items.value = ArrayList(goalsResult.data)
            } else if (goalsResult is Error) {
                _items.value = goalsResult.data ?: emptyList()
                showSnackbarMessage(R.string.loading_error)
            }

            _dataLoading.value = false
        }
    }

    private fun showSnackbarMessage(message: Int) {
        _snackbarText.value = message
    }

    fun refresh() {
        loadGoals(true)
    }

    fun openGoal(goal: Goal) {
        _openGoalEvent.value = goal
    }
}
