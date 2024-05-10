package com.bochi.kito.timbernotejava.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bochi.kito.timbernotejava.data.dao.NotesDao
import com.bochi.kito.timbernotejava.data.dao.TaskDao
import com.bochi.kito.timbernotejava.data.entity.Notes
import com.bochi.kito.timbernotejava.data.entity.Task
import com.bochi.kito.timbernotejava.tools.AppConstString.DATABASE_VERSION

@Database(
    entities = [Notes::class, Task::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NotesDao
    abstract fun taskDao(): TaskDao
}