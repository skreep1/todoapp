package com.skreep.todoapp.screens.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.skreep.todoapp.R
import com.skreep.todoapp.application.NoteApplication
import com.skreep.todoapp.model.Note
import com.skreep.todoapp.screens.add.AddActivity
import com.skreep.todoapp.viewmodel.NoteViewModel
import com.skreep.todoapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private val newNoteActivityRequestCode = 1
    private val wordViewModel: NoteViewModel by viewModels {
       NoteViewModelFactory((application as NoteApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NoteListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        wordViewModel.allNote.observe(this) { words ->
            words.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivityForResult(intent, newNoteActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(AddActivity.EXTRA_REPLY)?.let { reply ->
                val note = Note(reply)
                wordViewModel.insert(note)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}