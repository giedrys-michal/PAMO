package pamo.bmicalc.ui.quiz;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuizQuestionsModel {
    private List<QuizQuestion> questions = new ArrayList<>();

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }

    public QuizQuestion getQuestionById(int id){
        QuizQuestion qq = null;
        for (QuizQuestion q : questions) {
            if (q.getQuestionId() == id)
                qq = q;
        }
        return qq;
    }
}
