package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTaskTitle: TextView = itemView.findViewById(R.id.textViewTaskTitle)
        val checkBoxDone: CheckBox = itemView.findViewById(R.id.checkBoxDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false))
    }

    fun addTask(t: Task) {
        tasks.add(t)
        notifyItemInserted(tasks.size-1)
    }

    fun deleteTask() {
        tasks.removeAll { it.isChecked }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(textViewTaskTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            textViewTaskTitle.paintFlags = textViewTaskTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            textViewTaskTitle.paintFlags = textViewTaskTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = tasks[position]
        holder.textViewTaskTitle.text = currentTask.title
        holder.checkBoxDone.isChecked = currentTask.isChecked
        toggleStrikeThrough(holder.textViewTaskTitle, currentTask.isChecked)
        // swaps check box status via toggleStrikeThrough()
        holder.checkBoxDone.setOnCheckedChangeListener {
            _, isChecked ->toggleStrikeThrough(holder.textViewTaskTitle, currentTask.isChecked)
            currentTask.isChecked = !currentTask.isChecked
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}