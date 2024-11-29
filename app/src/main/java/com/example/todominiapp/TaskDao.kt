package com.example.todominiapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Tasks)


    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): MutableList<Tasks>


    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Long): Tasks?

    @Update()
    suspend fun updateTask(task: Tasks)

    @Delete()
    suspend fun DeleteTask(task: Tasks)
}