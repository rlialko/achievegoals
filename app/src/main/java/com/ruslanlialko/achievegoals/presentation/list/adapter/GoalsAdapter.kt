package com.ruslanlialko.achievegoals.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ruslanlialko.achievegoals.data.Goal
import com.ruslanlialko.achievegoals.databinding.GoalItemBinding
import com.ruslanlialko.achievegoals.presentation.list.GoalsListViewModel


class GoalsAdapter(
    private val viewModel: GoalsListViewModel
) : ListAdapter<Goal, GoalsAdapter.ViewHolder>(GoalDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel, getItem(position))
    }

    class ViewHolder private constructor(private val binding: GoalItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: GoalsListViewModel, item: Goal) {
            binding.viewmodel = viewModel
            binding.goal = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) = ViewHolder(
                GoalItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    class GoalDiffCallback : DiffUtil.ItemCallback<Goal>() {
        override fun areItemsTheSame(oldItem: Goal, newItem: Goal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Goal, newItem: Goal): Boolean {
            return oldItem == newItem
        }
    }
}