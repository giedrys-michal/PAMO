package pamo.bmicalc.ui.quiz;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;


import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class QuizViewModel extends ViewModel {
    private QuestionsXMLparser qxp;

    public QuizViewModel() {
        Log.v("QuizViewModel", "Entered -> QuizViewModel constructor.");
//        try {
//            qxp = new QuestionsXMLparser(ctx.getAssets().open("quiz.xml"));
//        } catch (IOException ex) {
//            System.out.println("Unable to read the file \"quiz.xml\", details: " + ex.getMessage());
//            Log.e("QuizViewModel", "Cannot open the file \"quiz.xml\", details: " + ex.getMessage());
//        }
    }
    public QuizQuestionsModel runParser(InputStream inputStream) throws XmlPullParserException, IOException{
        Log.v("QuizViewModel", "Entered -> runParser");
        qxp = new QuestionsXMLparser(inputStream);
        qxp.parseXML();

        return qxp.getQqm();
    }








}