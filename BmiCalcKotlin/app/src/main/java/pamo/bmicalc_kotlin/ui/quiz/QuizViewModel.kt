package pamo.bmicalc_kotlin.ui.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream

class QuizViewModel : ViewModel() {
    private var qxp: QuestionsXMLparser? = null
    @Throws(XmlPullParserException::class, IOException::class)
    fun runParser(inputStream: InputStream?): QuizQuestionsModel? {
        Log.v("QuizViewModel", "Entered -> runParser")
        qxp = QuestionsXMLparser(inputStream!!)
        qxp!!.parseXML()
        return qxp!!.getQqm()
    }

    init {
        Log.v("QuizViewModel", "Entered -> QuizViewModel constructor.")
    }
}