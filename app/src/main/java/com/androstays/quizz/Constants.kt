package com.androstays.quizz

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    var passCount = 0
    var questionCount = getQuestions().size

    fun getQuestions(): ArrayList<Question> {
        var questionList: ArrayList<Question>

        val que1 = Question(
            1,
            "Which country does this flag belongs to ?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Australia",
            "Armenia",
            "Austria",
            1
        )

        val que2 = Question(
            2,
            "Which country does this flag belongs to ?",
            R.drawable.ic_flag_of_australia,
            "Argentina",
            "Australia",
            "Armenia",
            "Austria",
            2
        )

        val que3 = Question(
            3,
            "Which country does this flag belongs to ?",
            R.drawable.ic_flag_of_belgium,
            "Belgium",
            "Australia",
            "Armenia",
            "India",
            1
        )

        val que4 = Question(
            4,
            "Which country does this flag belongs to ?",
            R.drawable.ic_flag_of_brazil,
            "Argentina",
            "Australia",
            "Brazil",
            "Belgium",
            3
        )

        val que5 = Question(
            5,
            "Which country does this flag belongs to ?",
            R.drawable.ic_flag_of_denmark,
            "Denmark",
            "Australia",
            "Armenia",
            "Austria",
            1
        )

        val que6 = Question(
            6,
            "Which country does this flag belongs to ?",
            R.drawable.ic_flag_of_fiji,
            "India",
            "Australia",
            "Armenia",
            "Fiji",
            4
        )
        val que7 = Question(
            7,
            "Which country does this flag belongs to ?",
            R.drawable.ic_flag_of_germany,
            "Argentina",
            "Australia",
            "Germany",
            "India",
            3
        )

        val que8 = Question(
            8,
            "Which country does this flag belongs to ?",
            R.drawable.ic_flag_of_india,
            "Argentina",
            "India",
            "Armenia",
            "Italy",
            2
        )

        val que9 = Question(
            9,
            "Which country does this flag belongs to ?",
            R.drawable.ic_flag_of_kuwait,
            "Argentina",
            "kuwait",
            "Armenia",
            "Germany",
            2
        )

        val que10 = Question(
            10,
            "Which country does this flag belongs to ?",
            R.drawable.ic_flag_of_new_zealand,
            "New Zealand",
            "Australia",
            "Armenia",
            "Austria",
            1
        )

        questionList = arrayListOf(que1,que2, que3, que4, que5, que6, que7, que8, que9, que10 )
        return questionList
    }
}
