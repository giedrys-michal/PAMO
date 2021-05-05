package pamo.bmicalc.ui.quiz;

import java.util.List;

public class QuizQuestion {
    private final int questionId;
    private final String questionText;
    private final List<QuizAnswer> answers;

    public QuizQuestion(int question_id, String question_text, List<QuizAnswer> answers) {
        this.questionId = question_id;
        this.questionText = question_text;
        this.answers = answers;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<QuizAnswer> getAnswers() {
        return answers;
    }
}
