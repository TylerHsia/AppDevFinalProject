package com.bignerdranch.com;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;


public class Question extends AppCompatActivity{
    private String mText;
    private boolean mAnswerTrue;
    private UUID mId;


    //add instance variable for whether the question has been answered
    //includes getters and setters
    private boolean mAnswered;

    //add instance variable for whether the question was answered correctly
    //includes getters and setters
    private boolean mCorrect;
    //cheated variable which keeps track of if a certain question has been cheated on
    private boolean mCheated;


    public Question(int textResId, boolean answerTrue){
        //mText = getResources().getString(textResId);
        mAnswerTrue = answerTrue;
        //set answered to false
        mAnswered = false;
        //set correct to false
        mCorrect = false;
        mCheated = false;
    }//*/

    public Question(String question, boolean answerTrue){
        mId = UUID.randomUUID();
        mText = question;
        mAnswerTrue = answerTrue;
        //set answered to false
        mAnswered = false;
        //set correct to false
        mCorrect = false;
        mCheated = false;
    }

    public String getText() {
        return mText;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public boolean isAnswered() { return mAnswered; }

    public boolean isCorrect() { return mCorrect; }

    public boolean isCheated() { return mCheated;}

    public void setText(String text){mText = text;}

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public UUID getId(){
        return mId;
    }

    public void setAnswered(boolean wasAnswered){ mAnswered = wasAnswered; }

    public void setCorrect(boolean wasCorrect){ mCorrect = wasCorrect; }

    public void setCheated(boolean cheated) {
        mCheated = cheated;
    }
}
