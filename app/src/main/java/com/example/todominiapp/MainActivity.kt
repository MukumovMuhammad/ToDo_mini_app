package com.example.todominiapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val rvTasks = findViewById<RecyclerView>(R.id.rvTasks)


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fab = findViewById<FloatingActionButton>(R.id.fab_new_task)

        fab.setOnClickListener{
            val Add_view = layoutInflater.inflate(R.layout.new_task_dialog, null)
            showAlertDialog(Add_view)
        }

    }

    private fun showAlertDialog(the_view: View) {

       val dialog =  AlertDialog.Builder(this)
            .setTitle("New Task")
            .setView(the_view)
            .setPositiveButton("Add"){_,_->
                AddNewTask(the_view)
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
        var main_view = findViewById<View>(R.id.main)
        if (title.text.isBlank() || discription.text.isBlank()){

            Snackbar.make(main_view, "Invalid input. Title or disctiption is empty!", Snackbar.LENGTH_LONG).show()

        }
    }


}