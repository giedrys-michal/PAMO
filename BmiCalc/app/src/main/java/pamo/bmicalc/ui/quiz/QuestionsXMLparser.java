package pamo.bmicalc.ui.quiz;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuestionsXMLparser {
    private static final String ns = null;
    private final InputStream xmlStream;
    private QuizQuestionsModel qqm;

    public QuizQuestionsModel getQqm() {
        return qqm;
    }

    public QuestionsXMLparser(InputStream xmlStream) {
        Log.v("QuestionsXMLparser", "Entered -> QuestionsXMLparser constructor.");
        this.xmlStream = xmlStream;
    }

    public void parseXML() throws XmlPullParserException, IOException {
        try {
            Log.v("QuestionsXMLparser", "Entered -> parseXML");
            XmlPullParserFactory pf = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = pf.newPullParser();
            xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xpp.setInput(xmlStream, null);
            xpp.nextTag();
            Log.v("QuestionsXMLparser", "Before processXML method.");
            processXML(xpp);
        } finally {
            Log.v("QuestionsXMLparser", "Closing InputStream.");
            xmlStream.close();
        }
    }

    private void processXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        Log.v("QuestionsXMLparser", "Entered -> processXML");
        qqm = new QuizQuestionsModel();
        QuizQuestion question = null;
        List<QuizQuestion> questions = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "quiz");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("question")) {
                question = parseQuestion(parser);
                Log.v("QuestionsXMLparser", "Question text retrieved: " + question.getQuestionText());
            } else {
                skip(parser);
            }
            questions.add(question);
        }
        qqm.setQuestions(questions);
        Log.v("QuestionsXMLparser", "Questions parsed: " + qqm.getQuestions().size());
    }

    private QuizQuestion parseQuestion(XmlPullParser parser) throws XmlPullParserException, IOException {
        int questionId = 0;
        String questionText = "";
        List<QuizAnswer> answers = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "question");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("question_id")) {
                questionId = Integer.parseInt(readTagContents(parser, "question_id"));
            } else if (name.equals("question_text")) {
                questionText = readTagContents(parser, "question_text");
            } else if (name.equals("answers")) {
                answers = parseAnswers(parser);
            } else {
                skip(parser);
            }
        }
        return new QuizQuestion(questionId, questionText, answers);
    }

    private List<QuizAnswer> parseAnswers(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<QuizAnswer> answers = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "answers");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("answer")) {
                answers.add(parseAnswer(parser));
            } else {
                skip(parser);
            }
        }
        return answers;
    }

    private QuizAnswer parseAnswer(XmlPullParser parser) throws XmlPullParserException, IOException {
        int answerId = 0;
        String answerText = "";
        boolean answerValid = false;

        parser.require(XmlPullParser.START_TAG, ns, "answer");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("answer_id")) {
                answerId = Integer.parseInt(readTagContents(parser, "answer_id"));
            } else if (name.equals("answer_text")) {
                answerText = readTagContents(parser, "answer_text");
            } else if (name.equals("answer_valid")) {
                answerValid = Boolean.parseBoolean(readTagContents(parser, "answer_valid"));
            } else {
                skip(parser);
            }
        }
        return new QuizAnswer(answerId, answerText, answerValid);
    }

    private String readTagContents(XmlPullParser parser, String tagName) throws XmlPullParserException, IOException {
        String result = "";

        parser.require(XmlPullParser.START_TAG, ns, tagName);
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }

        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
