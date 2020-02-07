package com.ruslanlialko.achievegoals.di

import com.ruslanlialko.achievegoals.presentation.details.GoalDetailsViewModel
import com.ruslanlialko.achievegoals.presentation.list.GoalsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var presentationModule = module {
    viewModel { GoalsListViewModel(get()) }
    viewModel { GoalDetailsViewModel(get()) }
}
