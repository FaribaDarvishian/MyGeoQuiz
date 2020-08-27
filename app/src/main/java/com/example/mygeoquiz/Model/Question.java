package com.example.mygeoquiz.Model;

import java.io.Serializable;
import java.util.UUID;

public class Question implements Serializable {
    private int mTextQuestion;
    private boolean mAnswerTrue;
    private boolean mIsAnswered;
    private boolean mCanCheat;
    private UUID mId;
    private String mQuestionTextColor;

    public Question() {

    }

    public void setIsAnswered(boolean answered) {
        mIsAnswered = answered;
    }

    public void setCanCheat(boolean canCheat) {
        mCanCheat = canCheat;
    }

    public void setQuestionTextColor(String questionTextColor) {
        mQuestionTextColor = questionTextColor;
    }

    public String getQuestionTextColor() {
        return mQuestionTextColor;
    }

    public boolean getCanCheat() {
        return mCanCheat;
    }

    public boolean getIsAnswered() {
        return mIsAnswered;
    }

    public int getTextQuestion() {
        return mTextQuestion;
    }

    public void setTextQuestion(int mTextQuestion) {
        this.mTextQuestion = mTextQuestion;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public boolean getAnswerTrue(){return mAnswerTrue;}
    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;

    }

    public UUID getId() {
        return mId;
    }

    public Question(int textQuestion, boolean answerTrue, boolean isAnswered, boolean canCheat) {
        mId=UUID.randomUUID();
        mTextQuestion = textQuestion;
        mAnswerTrue = answerTrue;
        mIsAnswered = isAnswered;
        mCanCheat = canCheat;
//        mQuestionTextColor = questionTextColor;
    }

}
