package pamo.bmicalc_kotlin.ui.quiz

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.util.ArrayList

class QuestionsXMLparser(xmlStream: InputStream) {
    private val xmlStream: InputStream
    private var qqm: QuizQuestionsModel? = null

    fun getQqm(): QuizQuestionsModel? {
        return this.qqm
    }

    @Throws(XmlPullParserException::class, IOException::class)
    fun parseXML() {
        try {
            Log.v("QuestionsXMLparser", "Entered -> parseXML")
            val pf = XmlPullParserFactory.newInstance()
            val xpp = pf.newPullParser()
            xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            xpp.setInput(xmlStream, null)
            xpp.nextTag()
            Log.v("QuestionsXMLparser", "Before processXML method.")
            processXML(xpp)
        } finally {
            Log.v("QuestionsXMLparser", "Closing InputStream.")
            xmlStream.close()
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun processXML(parser: XmlPullParser) {
        Log.v("QuestionsXMLparser", "Entered -> processXML")
        qqm = QuizQuestionsModel()
        var question: QuizQuestion? = null
        val questions: MutableList<QuizQuestion?> = ArrayList()
        parser.require(XmlPullParser.START_TAG, ns, "quiz")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            val name = parser.name
            if (name == "question") {
                question = parseQuestion(parser)
                Log.v("QuestionsXMLparser", "Question text retrieved: " + question.questionText)
            } else {
                skip(parser)
            }
            questions.add(question)
        }
        qqm!!.questions = questions
        Log.v("QuestionsXMLparser", "Questions parsed: " + qqm!!.questions.size)
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun parseQuestion(parser: XmlPullParser): QuizQuestion {
        var questionId = 0
        var questionText = ""
        var answers: MutableList<QuizAnswer?> = ArrayList()
        parser.require(XmlPullParser.START_TAG, ns, "question")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "question_id" -> {
                    questionId = readTagContents(parser, "question_id").toInt()
                }
                "question_text" -> {
                    questionText = readTagContents(parser, "question_text")
                }
                "answers" -> {
                    answers = parseAnswers(parser)
                }
                else -> {
                    skip(parser)
                }
            }
        }
        return QuizQuestion(questionId, questionText, answers)
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun parseAnswers(parser: XmlPullParser): MutableList<QuizAnswer?> {
        val answers: MutableList<QuizAnswer?> = ArrayList()
        parser.require(XmlPullParser.START_TAG, ns, "answers")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            val name = parser.name
            if (name == "answer") {
                answers.add(parseAnswer(parser))
            } else {
                skip(parser)
            }
        }
        return answers
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun parseAnswer(parser: XmlPullParser): QuizAnswer {
        var answerId = 0
        var answerText = ""
        var answerValid = false
        parser.require(XmlPullParser.START_TAG, ns, "answer")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "answer_id" -> {
                    answerId = readTagContents(parser, "answer_id").toInt()
                }
                "answer_text" -> {
                    answerText = readTagContents(parser, "answer_text")
                }
                "answer_valid" -> {
                    answerValid = readTagContents(parser, "answer_valid").toBoolean()
                }
                else -> {
                    skip(parser)
                }
            }
        }
        return QuizAnswer(answerId, answerText, answerValid)
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readTagContents(parser: XmlPullParser, tagName: String): String {
        var result = ""
        parser.require(XmlPullParser.START_TAG, ns, tagName)
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun skip(parser: XmlPullParser) {
        check(parser.eventType == XmlPullParser.START_TAG)
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }

    companion object {
        private val ns: String? = null
    }

    init {
        Log.v("QuestionsXMLparser", "Entered -> QuestionsXMLparser constructor.")
        this.xmlStream = xmlStream
    }
}