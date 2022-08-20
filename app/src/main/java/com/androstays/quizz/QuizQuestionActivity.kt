package com.androstays.quizz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var isSelected: Boolean = false

    private var cautionMessage: TextView? = null
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var tvImage: ImageView? = null


    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null

    private var submitBtn: Button? = null
    private var mUserName: String? = null
    private var btnSkip: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progressText)
        tvQuestion = findViewById(R.id.tv_question)
        tvImage = findViewById(R.id.iv_image)

        tvOptionOne = findViewById(R.id.option1)
        tvOptionTwo = findViewById(R.id.option2)
        tvOptionThree = findViewById(R.id.option3)
        tvOptionFour = findViewById(R.id.option4)
        submitBtn = findViewById(R.id.btnSubmit)
        cautionMessage = findViewById(R.id.cautionMsg)
        mQuestionList = Constants.getQuestions()
        btnSkip = findViewById(R.id.btnSkip)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        submitBtn?.setOnClickListener(this)
        btnSkip?.setOnClickListener(this)
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        setQuestion()
    }

    private fun setQuestion() {
        defaultOptionsView()
        val question = mQuestionList!![mCurrentPosition - 1]
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question
        tvImage?.setImageResource(question.image)
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if (mCurrentPosition == mQuestionList!!.size) {
            submitBtn?.text = "FINISH"
        } else {
            submitBtn?.text = "SUBMIT"
        }

        println(question)
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }

        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }

        tvOptionFour?.let {
            options.add(3, it)
        }

        options.forEach { x ->
            x.setTextColor(Color.parseColor("#7A8089"))
            x.typeface = Typeface.DEFAULT
            x.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

    }

    override fun onClick(v: View?) {
        cautionMessage?.text = ""
        when (v?.id) {
            R.id.option1 -> tvOptionOne?.let { selectedOptionView(it, 1) }
            R.id.option2 -> tvOptionTwo?.let { selectedOptionView(it, 2) }
            R.id.option3 -> tvOptionThree?.let { selectedOptionView(it, 3) }
            R.id.option4 -> tvOptionFour?.let { selectedOptionView(it, 4) }
            R.id.btnSkip -> {
                if (mCurrentPosition == mQuestionList!!.size) {
                    val intent = Intent(this, VictoryActivity::class.java)
                    intent.putExtra(Constants.USER_NAME, mUserName)
                    startActivity(intent)
                    finish()
                } else {
                    mCurrentPosition++
                    setQuestion()
                }
            }
            R.id.btnSubmit -> {
                if (isSelected) {
                    if (mSelectedOptionPosition == 0) {
                        mCurrentPosition++
                        when {
                            mCurrentPosition <= mQuestionList!!.size -> {
                                isSelected = false
                                setQuestion()
                                defaultOptionsView()
                            }
                        }
                    } else {
                        val question = mQuestionList?.get(mCurrentPosition - 1)
                        if (question!!.correctAnswer != mSelectedOptionPosition) {
                            answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                        }
                        answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                        if (question!!.correctAnswer == mSelectedOptionPosition) {
                            Constants.passCount++;
                        }
                        println("currentPosition:$mCurrentPosition and size is ${mQuestionList!!.size}")
                        if (mCurrentPosition != mQuestionList!!.size) {
                            submitBtn?.text = "Go to Next Question"
                        } else {
                            submitBtn?.text = "FINISH"
                            val intent = Intent(this, VictoryActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            startActivity(intent)
                            finish()
                        }
                        mSelectedOptionPosition = 0
                    }
                } else {
                    cautionMessage?.text = "Please select an option to proceed!!!"
                }

            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> tvOptionOne?.background = ContextCompat.getDrawable(this, drawableView)
            2 -> tvOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            3 -> tvOptionThree?.background = ContextCompat.getDrawable(this, drawableView)
            4 -> tvOptionFour?.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        isSelected = true
        tv.setTextColor(Color.parseColor("#363A43"))
        mSelectedOptionPosition = selectedOptionNum
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }
}