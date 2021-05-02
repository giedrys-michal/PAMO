package pamo.bmicalc.ui.quiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import pamo.bmicalc.R;

public class QuizFragment extends Fragment {

    private QuizViewModel quizViewModel;
    private QuizQuestionsModel qqm;

    private String test = "";

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
        test = qqm.getQuestionById(1).getQuestionText();

        tv_question_text = (TextView) view.findViewById(R.id.quiz_question_text);
        tv_question_text.setText(test);


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
}