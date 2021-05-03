package pamo.bmicalc.ui.quiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pamo.bmicalc.R;

public class QuizFragment extends Fragment {

    private QuizViewModel quizViewModel;
    private QuizQuestionsModel qqm;

    private String validAnswerText;
    private int quizResult = 0;
    private int questionCount;
    private int currentQuestion = 1;

    private TextView tv_question_text;
    private RadioButton rb_answer_1, rb_answer_2, rb_answer_3, rb_answer_4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.v("QuizFragment", "Entered -> onCreateView");
        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        initialiseParser();
        View root = inflater.inflate(R.layout.fragment_quiz, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.v("QuizFragment", "Entered -> onViewCreated");
        questionCount = qqm.getQuestions().size();
        Log.v("QuizFragment", "onViewCreated -> questionCount: " + questionCount);
        renderQuestion(view, qqm, 1);
        nextQuestionHandler(view);
    }

    public void initialiseParser() {
        Log.v("QuizFragment", "Entered -> initialiseParser");
        try {
            qqm = quizViewModel.runParser(this.getContext().getAssets().open("quiz.xml"));
        } catch (IOException ex) {
            System.out.println("Unable to read the file \"quiz.xml\", details: " + ex.getMessage());
            Log.v("QuizFragment", "Cannot open the file \"quiz.xml\", details: " + ex.getMessage());
        } catch (XmlPullParserException ex) {
            System.out.println("Parsing XML file \"quiz.xml\" unsuccessful, details: " + ex.getMessage());
            Log.v("QuizFragment", "Parsing XML file \"quiz.xml\" unsuccessful, details: " + ex.getMessage());
        }
    }

    public void renderQuestion(View view, QuizQuestionsModel qqm, int questionId) {
        Log.v("QuizFragment", "Entered -> renderQuestion");
        QuizQuestion q = qqm.getQuestionById(questionId);
        tv_question_text = (TextView) view.findViewById(R.id.quiz_question_text);
        tv_question_text.setText(q.getQuestionText());

        List<RadioButton> radioButtons = new ArrayList<>();
        radioButtons.add((RadioButton) view.findViewById(R.id.quiz_radio_1));
        radioButtons.add((RadioButton) view.findViewById(R.id.quiz_radio_2));
        radioButtons.add((RadioButton) view.findViewById(R.id.quiz_radio_3));
        radioButtons.add((RadioButton) view.findViewById(R.id.quiz_radio_4));

        List<QuizAnswer> answerSet = new ArrayList<>();
        answerSet = q.getAnswers();
        Collections.shuffle(answerSet);

        for (int i = 0; i < radioButtons.size(); i++) {
            QuizAnswer qa = answerSet.get(i);
            Log.v("QuizFragment", "for loop -> iteration " + i + "; Answer text: " + qa.getAnswer_text());
            boolean isAnswerValid = qa.isAnswer_valid();
            if (isAnswerValid)
                validAnswerText = qa.getAnswer_text();

            radioButtons.get(i).setText(qa.getAnswer_text());
        }
    }

    public void increaseResultIfCorrect(View view) {
        Log.v("QuizFragment", "Entered -> increaseResultIfCorrect");
        RadioGroup rg = view.findViewById(R.id.quiz_radioGroup_answers);
        int selection = rg.getCheckedRadioButtonId();
        RadioButton selectedAnswer = (RadioButton) view.findViewById(selection);
        if (selectedAnswer.getText() == validAnswerText) {
            Log.v("QuizFragment", "increaseResultIfCorrect -> answer correct, result increased.");
            quizResult++;
        }
    }

    public void nextQuestionHandler(View view) {
        Log.v("QuizFragment", "Entered -> nextQuestionHandler");
        Button next = (Button) view.findViewById(R.id.quiz_btn_next);
        if (currentQuestion == questionCount) {
            next.setText(R.string.quiz_btn_finish);
            // Display results
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion < questionCount) {
                    currentQuestion++;
                    Log.v("QuizFragment", "rendering question: " + currentQuestion);
                    increaseResultIfCorrect(view);
                    renderQuestion(view, qqm, currentQuestion);
                    if (currentQuestion == questionCount) {
                        next.setText(R.string.quiz_btn_finish);
                    }
                } else {
                    // Display results
                }
            }
        });
    }
}