package com.example.todominiapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(val tasks: ArrayList<Tasks>, val listener: onTaskClickListener): RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    var selectedItem  = -1
    interface onTaskClickListener{
        fun onLongClick(position: Int)
        fun onTaskClick(position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val task_view: View = LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false)
        return ViewHolder(task_view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = tasks[position].title
        holder.disc.text = tasks[position].disc

        if (tasks[position].isDone){
            holder.itemView.setBackgroundColor(Color.LTGRAY)
        }else{
            holder.itemView.setBackgroundColor(Color.WHITE)
        }

        holder.itemView.setOnLongClickListener {
            listener.onLongClick(position)
            true
        }
        holder.itemView.setOnClickListener {
            listener.onTaskClick(position)
        }



    }

    override fun getItemCount(): Int = tasks.size

    fun removeItem(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.task_title)
        val disc = itemView.findViewById<TextView>(R.id.task_disc)

    }
}