package com.bignerdranch.com;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionBank {
    private static QuestionBank sQuestionBank;
    private Context context;
    //initialize the question bank with these questions
    private List<Question> mQuestionBank;



    public QuestionBank(Context contextIn){
        mQuestionBank = new ArrayList<Question>();

        context = contextIn;
        mQuestionBank.add(new Question(context.getResources().getString(R.string.question_australia), true));
        mQuestionBank.add(new Question(context.getResources().getString(R.string.question_oceans), true));
        mQuestionBank.add(new Question(context.getResources().getString(R.string.question_mideast), false));
        mQuestionBank.add(new Question(context.getResources().getString(R.string.question_africa), false));
        mQuestionBank.add(new Question(context.getResources().getString(R.string.question_americas), true));
        mQuestionBank.add(new Question(context.getResources().getString(R.string.question_asia), true));

    }

    public static QuestionBank get(Context context){
        if(sQuestionBank == null){
            sQuestionBank = new QuestionBank(context);
        }
        return sQuestionBank;
    }

    public Question getQuestion(UUID questionID) {
        for (Question question : mQuestionBank) {
            if(question.getId().equals(questionID)){
                return question;
            }
        }
        return null;
    }

    public Question getQuestion(int index){
        return mQuestionBank.get(index);
    }


    //question bank getter
    public List<Question> getQuestionBank(){
        return mQuestionBank;
    }

    //set one question
    public void setQuestion(boolean answer, String question, int index){

        mQuestionBank.set(index, new Question(question, answer));
    }

    //length getter
    public int getLength(){
        return mQuestionBank.size();
    }

    //method which checks whether every question stored in question bank has been answered
    public boolean allAnswered(){
        for(int i = 0; i < mQuestionBank.size(); i++){
            if(!mQuestionBank.get(i).isAnswered()){
                return false;
            }
        }
        return true;
    }

    //method that counts the total number of correct questions in question bank
    public int numCorrect(){
        int num = 0;
        for(int i = 0; i < mQuestionBank.size(); i++){
            if(mQuestionBank.get(i).isCorrect()){
                num++;
            }
        }
        return num;
    }

    //return if answer is true for a given index
    public boolean isAnswerTrue(int currentIndex) {
        return mQuestionBank.get(currentIndex).isAnswerTrue();
    }

    //sets a specified question as has been cheated on or not
    public void setCheated(int currentIndex, boolean b) {
        mQuestionBank.get(currentIndex).setCheated(b);
    }

    //returns if a question was cheated on for a given index
    public boolean isCheated(int currentIndex) {
        return mQuestionBank.get(currentIndex).isCheated();
    }

    //returns test of a question for a given index
    public String getText(int currentIndex) {
        return mQuestionBank.get(currentIndex).getText();
    }
}
