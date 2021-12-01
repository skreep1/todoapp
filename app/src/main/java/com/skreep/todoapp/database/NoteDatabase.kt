package com.skreep.todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skreep.todoapp.model.Note
import kotlinx.coroutines.CoroutineScope


@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
    abstract class NoteDatabase : RoomDatabase() {

        abstract fun noteDao(): NoteDao


        companion object {
            @Volatile
            private var INSTANCE: NoteDatabase? = null

            fun getDatabase(
                context: Context,
                scope: CoroutineScope
            ): NoteDatabase {

                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database"
                    )
                        .build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
            }
        }
    }
