package com.skreep.todoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.skreep.todoapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun getNote(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()
}