package pamo.bmicalc_kotlin.ui.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.xmlpull.v1.XmlPullParserException
import pamo.bmicalc_kotlin.R
import java.io.IOException
import java.util.*
import java.util.Collections.shuffle

class QuizFragment : Fragment() {
    private var quizViewModel: QuizViewModel? = null
    private var qqm: QuizQuestionsModel? = null
    private var validAnswerText: String? = null
    private var quizResult = 0
    private var questionCount = 0
    private var currentQuestion = 1
    private var tvQuestionText: TextView? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.v("QuizFragment", "Entered -> onCreateView")
        quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        initialiseParser()
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v("QuizFragment", "Entered -> onViewCreated")
        questionCount = qqm!!.questions.size
        Log.v("QuizFragment", "onViewCreated -> questionCount: $questionCount")
        renderQuestion(view, qqm, currentQuestion)
        nextQuestionHandler(view)
        retakeQuizHandler(view)
    }

    private fun initialiseParser() {
        Log.v("QuizFragment", "Entered -> initialiseParser")
        try {
            qqm = quizViewModel!!.runParser(this.requireContext().assets.open("quiz.xml"))
        } catch (ex: IOException) {
            println("Unable to read the file \"quiz.xml\", details: " + ex.message)
            Log.v("QuizFragment", "Cannot open the file \"quiz.xml\", details: " + ex.message)
        } catch (ex: XmlPullParserException) {
            println("Parsing XML file \"quiz.xml\" unsuccessful, details: " + ex.message)
            Log.v("QuizFragment", "Parsing XML file \"quiz.xml\" unsuccessful, details: " + ex.message)
        }
    }

    private fun renderQuestion(view: View, qqm: QuizQuestionsModel?, questionId: Int) {
        Log.v("QuizFragment", "Entered -> renderQuestion")
        val q = qqm!!.getQuestionById(questionId)
        tvQuestionText = view.findViewById<View>(R.id.quiz_question_text) as TextView
        tvQuestionText!!.text = q!!.questionText
        val radioButtons: MutableList<RadioButton> = ArrayList()
        radioButtons.add(view.findViewById<View>(R.id.quiz_radio_1) as RadioButton)
        radioButtons.add(view.findViewById<View>(R.id.quiz_radio_2) as RadioButton)
        radioButtons.add(view.findViewById<View>(R.id.quiz_radio_3) as RadioButton)
        radioButtons.add(view.findViewById<View>(R.id.quiz_radio_4) as RadioButton)
        val answerSet: List<QuizAnswer?>
        answerSet = q.getAnswers()
        shuffle(answerSet)
        for (i in radioButtons.indices) {
            val qa = answerSet[i]
            Log.v("QuizFragment", "for loop -> iteration " + i + "; Answer text: " + qa!!.getAnswerText())
            val isAnswerValid: Boolean = qa.isAnswerValid()
            if (isAnswerValid) validAnswerText = qa.getAnswerText()
            radioButtons[i].text = qa.getAnswerText()
        }
        setQuestionCount(view)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestionCount(view: View) {
        val tv = view.findViewById<View>(R.id.quiz_questionCount_text) as TextView
        tv.text = "$currentQuestion / $questionCount"
    }

    private fun increaseResultIfCorrect(view: View) {
        Log.v("QuizFragment", "Entered -> increaseResultIfCorrect")
        val rg = view.findViewById<View>(R.id.quiz_radioGroup_answers) as RadioGroup
        val selection = rg.checkedRadioButtonId
        val selectedAnswer = view.findViewById<View>(selection) as RadioButton
        if (selectedAnswer.text === validAnswerText) {
            Log.v("QuizFragment", "increaseResultIfCorrect -> answer correct, result increased.")
            quizResult++
        }
    }

    private fun nextQuestionHandler(view: View) {
        Log.v("QuizFragment", "Entered -> nextQuestionHandler")
        val btnNext = view.findViewById<View>(R.id.quiz_btn_next) as Button
        if (currentQuestion == questionCount) {
            btnNext.setText(R.string.quiz_btn_finish)
        }
        btnNext.setOnClickListener {
            increaseResultIfCorrect(view)
            if (currentQuestion < questionCount) {
                currentQuestion++
                Log.v("QuizFragment", "rendering question: $currentQuestion")
                renderQuestion(view, qqm, currentQuestion)
                if (currentQuestion == questionCount) {
                    btnNext.setText(R.string.quiz_btn_finish)
                }
            } else {
                displayResults(view)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayResults(view: View) {
        val tvDisplayresult = view.findViewById<View>(R.id.quiz_question_text) as TextView
        val rgAnswers = view.findViewById<View>(R.id.quiz_radioGroup_answers) as RadioGroup
        val btnFinnish = view.findViewById<View>(R.id.quiz_btn_next) as Button
        rgAnswers.visibility = View.INVISIBLE
        tvDisplayresult.text = "Result: $quizResult / $questionCount"
        btnFinnish.isEnabled = false
    }

    private fun retakeQuizHandler(view: View) {
        Log.v("QuizFragment", "Entered -> retakeQuizHandler")
        val btnRetake = view.findViewById<View>(R.id.quiz_btn_retake) as Button
        val btnNext = view.findViewById<View>(R.id.quiz_btn_next) as Button
        val rgAnswers = view.findViewById<View>(R.id.quiz_radioGroup_answers) as RadioGroup
        btnRetake.setOnClickListener {
            btnNext.setText(R.string.quiz_btn_next)
            btnNext.isEnabled = true
            rgAnswers.visibility = View.VISIBLE
            quizResult = 0
            currentQuestion = 1
            validAnswerText = ""
            renderQuestion(view, qqm, currentQuestion)
        }
    }
}