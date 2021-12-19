package com.itis.android_homework.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itis.android_homework.data.dao.TaskDao
import com.itis.android_homework.data.entity.DateConverter
import com.itis.android_homework.data.entity.Task

@Database(entities = [Task::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        private const val DB_NAME = "task.db"
        private val dateConverterInstance = DateConverter()
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if(instance == null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .addTypeConverter(dateConverterInstance)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}
