package com.example.todominiapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), TasksAdapter.onTaskClickListener  {

    private lateinit var  rvTasks : RecyclerView
    private lateinit var  tasks : ArrayList<Tasks>
    private lateinit var  main_view : View


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
        rvTasks.layoutManager = LinearLayoutManager(this)
        main_view = findViewById<View>(R.id.main)

        tasks = ArrayList<Tasks>()
        val task_Adapter = TasksAdapter(tasks, this)
        rvTasks.adapter = task_Adapter

        val fab = findViewById<FloatingActionButton>(R.id.fab_new_task)

        fab.setOnClickListener{
            val Add_view = layoutInflater.inflate(R.layout.new_task_dialog, null)
            showAlertDialog(Add_view, "New Task", View.OnClickListener { AddNewTask(Add_view) })
        }

        val TasksTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                task_Adapter.removeItem(viewHolder.adapterPosition)
            }

        })

        TasksTouchHelper.attachToRecyclerView(rvTasks)


    }

    private fun showAlertDialog(the_view: View, title :String, positiveBtn: View.OnClickListener) {

       val dialog =  AlertDialog.Builder(this)
            .setTitle(title)
            .setView(the_view)
            .setPositiveButton("Confirm"){_,_->
                positiveBtn.onClick(null)
            }
            .setNegativeButton("Cancel"){dialogInterface, _->
                dialogInterface.dismiss()
            }
            .create()
        dialog.show()
    }
    


    private fun AddNewTask(the_view: View) {
        val title = the_view.findViewById<EditText>(R.id.inp_title)
        val discription = the_view.findViewById<EditText>(R.id.inp_disc)

        if (title.text.isBlank() || discription.text.isBlank()){

            Snackbar.make(main_view, "Invalid input. Title or disctiption is empty!", Snackbar.LENGTH_LONG).show()
            return
        }

        val new_task = Tasks(title.text.toString(), discription.text.toString(), false)
        tasks.add(new_task)
        rvTasks.adapter?.notifyItemInserted(tasks.size -1)

        Snackbar.make(main_view, "Task Added", Snackbar.LENGTH_LONG).show()


    }

    override fun onTaskClick(position: Int) {
        if (tasks[position].isDone){
            tasks[position].isDone = false
        }else{
            tasks[position].isDone = true
        }
        rvTasks.adapter?.notifyDataSetChanged()
    }

    override fun onLongClick(position: Int){
        val Add_view = layoutInflater.inflate(R.layout.new_task_dialog, null)
        showAlertDialog(Add_view, "Change Task ${tasks[position].title}", View.OnClickListener { EditTask(Add_view, position) })
    }

    private fun EditTask(the_view: View, position: Int) {
        val title = the_view.findViewById<EditText>(R.id.inp_title)
        val discription = the_view.findViewById<EditText>(R.id.inp_disc)

        if (title.text.isBlank() || discription.text.isBlank()){

            Snackbar.make(main_view, "Invalid input. Title or disctiption is empty!", Snackbar.LENGTH_LONG).show()
            return
        }

        tasks[position].title = title.text.toString()
        tasks[position].disc = discription.text.toString()
        tasks[position].isDone = false

        rvTasks.adapter?.notifyDataSetChanged()
        Snackbar.make(main_view, "Task is changed", Snackbar.LENGTH_LONG).show()
    }


}