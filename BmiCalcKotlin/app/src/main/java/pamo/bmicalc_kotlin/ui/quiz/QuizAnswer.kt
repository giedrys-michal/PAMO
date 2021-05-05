package pamo.bmicalc_kotlin.ui.quiz

class QuizAnswer(answerId: Int, answerText: String, answerValid: Boolean) {
    private var answerId: Int? = null
    private var answerText: String = ""
    private var answerValid: Boolean = false

    init {
        this.answerId = answerId
        this.answerText = answerText
        this.answerValid = answerValid
    }

    fun getAnswerText(): String {
        return this.answerText
    }

    fun isAnswerValid(): Boolean {
        return this.answerValid
    }
}