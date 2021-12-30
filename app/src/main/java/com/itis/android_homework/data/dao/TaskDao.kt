package com.itis.android_homework.data.dao

import androidx.room.*
import com.itis.android_homework.data.entity.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Query("SELECT * FROM task")
    suspend fun getAllTasks(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("DELETE FROM task WHERE id=:id")
    suspend fun deleteTaskById(id: Int)

    @Query("DELETE FROM task")
    suspend fun deleteAllTasks()
}
