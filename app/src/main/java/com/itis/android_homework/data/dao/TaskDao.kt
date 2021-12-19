package com.itis.android_homework.data.dao

import androidx.room.*
import com.itis.android_homework.data.entity.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task WHERE id = :id")
    fun getTaskById(id: Int): Task?

    @Query("SELECT * FROM task")
    fun getAllTasks(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("DELETE FROM task WHERE id=:id")
    fun deleteTaskById(id: Int)

    @Query("DELETE FROM task")
    fun deleteAllTasks()
}
