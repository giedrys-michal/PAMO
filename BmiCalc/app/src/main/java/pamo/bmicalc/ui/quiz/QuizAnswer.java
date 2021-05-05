package pamo.bmicalc.ui.quiz;

public class QuizAnswer {
    private int answer_id;
    private String answer_text;
    private boolean answer_valid;

    public QuizAnswer(int answer_id, String answer_text, boolean answer_valid) {
        this.answer_id = answer_id;
        this.answer_text = answer_text;
        this.answer_valid = answer_valid;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public boolean isAnswer_valid() {
        return answer_valid;
    }
}
