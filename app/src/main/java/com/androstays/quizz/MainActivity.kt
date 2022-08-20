package com.androstays.quizz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.start)
        val editText: EditText = findViewById(R.id.nameTextBox)

        startButton.setOnClickListener {
            if (editText.text.isEmpty()) {
                Toast.makeText(this, "please enter a valid name...", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME, editText.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}