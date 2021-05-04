package pamo.bmicalc.ui.quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizQuestion {
    private int questionId;
    private String questionText;
    private List<QuizAnswer> answers = new ArrayList<>();

    public QuizQuestion(int question_id, String question_text, List<QuizAnswer> answers) {
        this.questionId = question_id;
        this.questionText = question_text;
        this.answers = answers;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<QuizAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuizAnswer> answers) {
        this.answers = answers;
    }
}
