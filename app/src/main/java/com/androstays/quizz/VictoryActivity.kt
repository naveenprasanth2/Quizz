package com.androstays.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class VictoryActivity : AppCompatActivity() {
    private var resultText: TextView? = null
    private var userName: TextView? = null
    private var victoryImage: ImageView? = null
    private var messageToUser: TextView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_victory)

        resultText = findViewById(R.id.resultText)
        userName = findViewById(R.id.userName)
        val finishBtn: Button = findViewById(R.id.finishBtn)
        victoryImage = findViewById(R.id.victoryTrophy)

        victoryImage?.setImageResource(R.drawable.ic_trophy)
        messageToUser = findViewById(R.id.messageToUser)

        userName?.text = intent.getStringExtra(Constants.USER_NAME)
        if(Constants.passCount> (Constants.getQuestions().size/2)){
            messageToUser?.text = "Hey Congratulations!"
        }else{
            messageToUser?.text = "Uh Huh!!!"
        }

        resultText?.text =
            "Your score is ${Constants.passCount} out of ${Constants.questionCount}"

        finishBtn.setOnClickListener {
            Constants.passCount = 0
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}