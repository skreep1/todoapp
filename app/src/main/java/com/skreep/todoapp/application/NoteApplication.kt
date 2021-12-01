package com.skreep.todoapp.application

import android.app.Application
import com.skreep.todoapp.database.NoteDatabase
import com.skreep.todoapp.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { NoteDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}
