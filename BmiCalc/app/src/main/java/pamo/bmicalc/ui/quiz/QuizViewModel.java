package pamo.bmicalc.ui.quiz;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class QuizViewModel extends ViewModel {

    public QuizViewModel() {
        Log.v("QuizViewModel", "Entered -> QuizViewModel constructor.");
    }
    public QuizQuestionsModel runParser(InputStream inputStream) throws XmlPullParserException, IOException{
        Log.v("QuizViewModel", "Entered -> runParser");
        QuestionsXMLparser qxp = new QuestionsXMLparser(inputStream);
        qxp.parseXML();

        return qxp.getQqm();
    }
}