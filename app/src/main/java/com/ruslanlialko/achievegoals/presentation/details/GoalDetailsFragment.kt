package com.ruslanlialko.achievegoals.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ruslanlialko.achievegoals.data.Goal
import com.ruslanlialko.achievegoals.databinding.FragmentGoalDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoalDetailsFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentGoalDetailsBinding
    private val goalViewModel by viewModel(GoalDetailsViewModel::class)
    private lateinit var goal: Goal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goal = arguments!!.getParcelable("goal")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        goalViewModel.setGoal(goal)
        (activity as AppCompatActivity).supportActionBar?.title = goal.title
        viewDataBinding = FragmentGoalDetailsBinding.inflate(inflater, container, false).apply {
            viewmodel = goalViewModel
            lifecycleOwner = this@GoalDetailsFragment.viewLifecycleOwner
        }

        return viewDataBinding.root
    }

}
