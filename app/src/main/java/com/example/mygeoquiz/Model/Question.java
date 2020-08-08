package com.example.mygeoquiz.Model;

public class Question {
    private int mQuestionTextResId;
    private boolean mIsAnswerTrue;
    private boolean mIsCheating;

    public boolean isIsCheating() {
        return mIsCheating;
    }

    public void setIsCheating(boolean mIsCheating) {
        this.mIsCheating = mIsCheating;
    }

    public boolean isIsAnswered() {
        return mIsAnswered;
    }

    public void setIsAnswered(boolean mIsAnswered) {
        this.mIsAnswered = mIsAnswered;
    }

    private boolean mIsAnswered;

    public int getQuestionTextResId() {
        return mQuestionTextResId;
    }

    public void setQuestionTextResId(int questionTextResId) {
        mQuestionTextResId = questionTextResId;
    }

    public boolean isAnswerTrue() {
        return mIsAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mIsAnswerTrue = answerTrue;
    }

    public Question(int questionTextResId, boolean isAnswerTrue) {
        mQuestionTextResId = questionTextResId;
        mIsAnswerTrue = isAnswerTrue;
    }

    public Question() {
    }
}
