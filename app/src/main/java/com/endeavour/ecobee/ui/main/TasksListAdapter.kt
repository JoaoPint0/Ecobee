package com.endeavour.ecobee.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.endeavour.ecobee.R
import com.endeavour.ecobee.databinding.ViewHolderTaskBinding
import com.endeavour.ecobee.models.Task

class TasksListAdapter(private val navigate: (Task) -> Unit) : ListAdapter<Task, TasksListAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_holder_task, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { game ->
            with(holder) {
                bind(navigate, game)
            }
        }
    }

    class ViewHolder(private val binding: ViewHolderTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(navigate: (Task) -> Unit, value: Task) {
            with(binding) {
                this.task = value
                this.clickListener = View.OnClickListener {
                    navigate.invoke(value)
                }
                executePendingBindings()
            }
        }
    }
}

private class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }
}
