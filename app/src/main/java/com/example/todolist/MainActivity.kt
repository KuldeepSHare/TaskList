package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        taskAdapter = TaskAdapter(mutableListOf())

        val rvTaskItems: RecyclerView = findViewById(R.id.recyclerViewTaskItems)

        rvTaskItems.adapter = taskAdapter
        rvTaskItems.layoutManager = LinearLayoutManager(this)

        val btnAddTask: Button = findViewById(R.id.buttonAddTask)
        btnAddTask.setOnClickListener {
            val taskTitle: EditText = findViewById(R.id.editTextTaskList)
            val stringTitle: String = taskTitle.text.toString()

            if(stringTitle.isNotEmpty()) {
                taskAdapter.addTask(Task(stringTitle))
                taskTitle.text.clear()
            }
        }

        val btnDeleteTask: Button = findViewById(R.id.buttonDeleteTask)
        btnDeleteTask.setOnClickListener {
            taskAdapter.deleteTask()
        }



    }
}