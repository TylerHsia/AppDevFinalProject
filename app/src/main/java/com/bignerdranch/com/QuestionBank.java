package com.bignerdranch.com;

import android.content.Context;

public class QuestionBank {
    private Context context;
    //initialize the question bank with these questions
    private Question[] mQuestionBank;



    public QuestionBank(Context contextIn){
        context = contextIn;
        context.getResources().getString(R.string.question_africa);
        mQuestionBank = new Question[]{

            new Question(context.getResources().getString(R.string.question_australia), true),
            new Question(context.getResources().getString(R.string.question_oceans), true),
            new Question(context.getResources().getString(R.string.question_mideast), false),
            new Question(context.getResources().getString(R.string.question_africa), false),
            new Question(context.getResources().getString(R.string.question_americas), true),
            new Question(context.getResources().getString(R.string.question_asia), true),
        };
    }

    //question bank getter
    public Question[] getQuestionBank(){
        return mQuestionBank;
    }

    //set one question
    public void setQuestion(boolean answer, String question, int index){

        mQuestionBank[index] = new Question(question, answer);
    }

    //length getter
    public int getLength(){
        return mQuestionBank.length;
    }

    //method which checks whether every question stored in question bank has been answered
    public boolean allAnswered(){
        for(int i = 0; i < mQuestionBank.length; i++){
            if(!mQuestionBank[i].isAnswered()){
                return false;
            }
        }
        return true;
    }

    //method that counts the total number of correct questions in question bank
    public int numCorrect(){
        int num = 0;
        for(int i = 0; i < mQuestionBank.length; i++){
            if(mQuestionBank[i].isCorrect()){
                num++;
            }
        }
        return num;
    }

    //return if answer is true for a given index
    public boolean isAnswerTrue(int currentIndex) {
        return mQuestionBank[currentIndex].isAnswerTrue();
    }

    //sets a specified question as has been cheated on or not
    public void setCheated(int currentIndex, boolean b) {
        mQuestionBank[currentIndex].setCheated(b);
    }

    //returns if a question was cheated on for a given index
    public boolean isCheated(int currentIndex) {
        return mQuestionBank[currentIndex].isCheated();
    }

    //returns test of a question for a given index
    public String getText(int currentIndex) {
        return mQuestionBank[currentIndex].getText();
    }
}
