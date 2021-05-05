package pamo.bmicalc_kotlin.ui.quiz

import java.util.*

class QuizQuestion(var questionId: Int, var questionText: String, answers: MutableList<QuizAnswer?>) {
    private var answers: MutableList<QuizAnswer?> = ArrayList()

    init {
        this.answers = answers
    }

    fun getAnswers(): MutableList<QuizAnswer?> {
        return this.answers
    }
}