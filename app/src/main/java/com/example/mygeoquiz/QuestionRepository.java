package com.example.mygeoquiz;

import com.example.mygeoquiz.Model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionRepository {
    private static QuestionRepository sQuestionRepository;
    private List<Question> mQuestions = new ArrayList<>();

    public static QuestionRepository getInstance() {
        if (sQuestionRepository == null) {
            sQuestionRepository = new QuestionRepository();
        }
        return sQuestionRepository;
    }

    public void setQuestions(List<Question> mQuestions) {
        this.mQuestions = mQuestions;
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
        mQuestions.add(new Question(R.string.question_australia, false,false,false));
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }

}
