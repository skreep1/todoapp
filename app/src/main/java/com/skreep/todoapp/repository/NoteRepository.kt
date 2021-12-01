package com.skreep.todoapp.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.skreep.todoapp.database.NoteDao
import com.skreep.todoapp.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {


    val allWords: Flow<List<Note>> = noteDao.getNote()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Note) {
        noteDao.insert(word)
    }
}
