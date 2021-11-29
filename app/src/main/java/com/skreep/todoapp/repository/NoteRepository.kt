package com.skreep.todoapp.repository

import androidx.annotation.WorkerThread
import com.skreep.todoapp.database.NoteDao
import com.skreep.todoapp.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }
}