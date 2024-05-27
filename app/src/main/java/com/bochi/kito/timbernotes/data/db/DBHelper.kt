package com.bochi.kito.timbernotes.data.db

import androidx.room.Room
import com.bochi.kito.timbernotes.App
import com.bochi.kito.timbernotes.data.entity.Notes
import com.bochi.kito.timbernotes.data.entity.Task
import com.bochi.kito.timbernotes.tools.AppConstString.DATABASE_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DBHelper {

    val notesDatabase: NotesDatabase by lazy {
        Room.databaseBuilder(
            App.appContext,
            NotesDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    fun closeDB() {
        notesDatabase.run {
            close()
        }
    }
//
//    fun getAllNote(): MutableLiveData<List<Notes>> {
//        CoroutineScope(Dispatchers.IO).launch {
//            notesDatabase.noteDao().getAllNotes()
//        }
//        return
//    }

    fun insertNote(note: Notes) {
        CoroutineScope(Dispatchers.IO).launch {
            notesDatabase.noteDao()
                .insertNote(note)
        }
    }

    fun updateNote(note: Notes) {
        CoroutineScope(Dispatchers.IO).launch {
            notesDatabase.noteDao()
                .updateNote(note)
        }
    }

    fun deleteNote(note: Notes) {
        CoroutineScope(Dispatchers.IO).launch {
            notesDatabase.noteDao()
                .deleteNote(note)
        }
    }


    fun insertTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            notesDatabase.taskDao()
                .insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            notesDatabase.taskDao()
                .updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            notesDatabase.taskDao()
                .deleteTask(task)
        }
    }
    

}