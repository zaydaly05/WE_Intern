package com.example.localpersistence.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.localpersistence.data.local.dao.TodoDao
import com.example.localpersistence.data.local.entity.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}