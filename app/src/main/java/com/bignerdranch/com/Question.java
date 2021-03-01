package com.bignerdranch.com;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    //add instance variable for whether the question has been answered
    //includes getters and setters
    private boolean mAnswered;

    //add instance variable for whether the question was answered correctly
    //includes getters and setters
    private boolean mCorrect;
    //cheated variable which keeps track of if a certain question has been cheated on
    private boolean mCheated;

    public Question(int textResId, boolean answerTrue){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        //set answered to false
        mAnswered = false;
        //set correct to false
        mCorrect = false;
        mCheated = false;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public boolean isAnswered() { return mAnswered; }

    public boolean isCorrect() { return mCorrect; }

    public boolean isCheated() { return mCheated;}

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public void setAnswered(boolean wasAnswered){ mAnswered = wasAnswered; }

    public void setCorrect(boolean wasCorrect){ mCorrect = wasCorrect; }

    public void setCheated(boolean cheated) {
        mCheated = cheated;
    }
}
