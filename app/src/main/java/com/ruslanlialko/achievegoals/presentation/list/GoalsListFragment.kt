package com.ruslanlialko.achievegoals.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ruslanlialko.achievegoals.databinding.FragmentGoalsListBinding
import com.ruslanlialko.achievegoals.presentation.list.adapter.GoalsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoalsListFragment : Fragment() {

    private val vm: GoalsListViewModel by viewModel()
    private lateinit var goalsBinding: FragmentGoalsListBinding
    private lateinit var goalsAdapter: GoalsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        goalsBinding = FragmentGoalsListBinding.inflate(inflater, container, false).apply {
            viewmodel = vm
        }
        return (goalsBinding as ViewDataBinding).root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (goalsBinding as ViewDataBinding).lifecycleOwner = this
        initRecyclerView()
        initSnackbar()
        initNavigation()
    }

    private fun initRecyclerView() {
        goalsAdapter = GoalsAdapter(vm)
        goalsBinding.rlGoals.apply {
            adapter = goalsAdapter
        }
    }

    private fun initSnackbar() {
        vm.snackbarText.observe(viewLifecycleOwner, Observer { text ->
            view?.let { Snackbar.make(it, text, Snackbar.LENGTH_SHORT).show() }
        })
    }

    private fun initNavigation() {
        vm.openGoalEvent.observe(this.viewLifecycleOwner, Observer {
            findNavController().navigate(
                GoalsListFragmentDirections.actionGoalsListFragmentToGoalDetailsFragment(
                    it
                )
            )
        })
    }
}
