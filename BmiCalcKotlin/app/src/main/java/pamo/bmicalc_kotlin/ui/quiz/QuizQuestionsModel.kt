package pamo.bmicalc_kotlin.ui.quiz

import java.util.ArrayList

class QuizQuestionsModel {
    var questions: MutableList<QuizQuestion?> = ArrayList()
    fun getQuestionById(id: Int): QuizQuestion? {
        var qq: QuizQuestion? = null
        for (q in questions) {
            if (q!!.questionId == id) qq = q
        }
        return qq
    }
}