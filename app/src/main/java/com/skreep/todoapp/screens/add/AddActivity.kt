package com.skreep.todoapp.screens.add


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.skreep.todoapp.R

class AddActivity : AppCompatActivity() {
    private lateinit var inputText: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        inputText = findViewById(R.id.edit_word)

        val button = findViewById<Button>(R.id.button_add)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(inputText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = inputText.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}