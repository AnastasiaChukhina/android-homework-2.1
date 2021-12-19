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

        @Volatile
        private var instance: AppDatabase? = null
        private val dateConverterInstance = DateConverter()
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .addTypeConverter(dateConverterInstance)
                    .fallbackToDestructiveMigration()
                    .build()
    }
}
