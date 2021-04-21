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

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mEditButton;
    private TextView mTextBox;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private static final String KEY_INDEX = "index";
    private static final String KEY_CHEATER = "cheater";
    private Button mCheatButton;
    private static final int REQUEST_CODE_CHEAT = 0;
    private boolean mIsCheater;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            //retrieve value for ischeater from savedinstancesate
            mIsCheater = savedInstanceState.getBoolean(KEY_CHEATER, false);
        }

        mQuestionTextView = (TextView) findViewById(R.id.text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                mIsCheater = false;
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                mIsCheater = false;
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        //EditButton onClickListener will open an edit activity
        mEditButton = (Button) findViewById((R.id.edit_button));
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            //if the user cheated, set the question mCheated value to true and isCheater to true
            if(CheatActivity.wasAnswerShown(data)){
                mQuestionBank[mCurrentIndex].setCheated(true);
                mIsCheater = true;
            }

        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        //store value for mIsCheater
        savedInstanceState.putBoolean(KEY_CHEATER, mIsCheater);
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        //if the current question has been cheated on
        if(mQuestionBank[mCurrentIndex].isCheated() || mIsCheater){
            messageResId = R.string.judgement_toast;
        } else{
            if (userPressedTrue == answerIsTrue){
                messageResId = R.string.correct_toast;
            } else{
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        /*
        //if the current question has not been answered
        if(!mQuestionBank[mCurrentIndex].isAnswered()) {
            if (userPressedTrue == answerIsTrue){
                messageResId = R.string.correct_toast;
                mQuestionBank[mCurrentIndex].setCorrect(true);
            }
            else{
                messageResId = R.string.incorrect_toast;
                mQuestionBank[mCurrentIndex].setCorrect(false);
            }
            //set current question to has been answered
            mQuestionBank[mCurrentIndex].setAnswered(true);
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

            //if all questions have been answered, display a toast showing percent correct
            if(allAnswered()){
                String percentToast = "You got %" + (double) numCorrect() / mQuestionBank.length * 100;
                Toast.makeText(this, percentToast, Toast.LENGTH_SHORT).show();
            }
        }
        //if question has already been answered, make toast informing user
        else
            Toast.makeText(this, "Already Answered", Toast.LENGTH_SHORT).show();

         */

    }

    //method which checks whether every question stored in question bank has been answered
    private boolean allAnswered(){
        for(int i = 0; i < mQuestionBank.length; i++){
            if(!mQuestionBank[i].isAnswered()){
                return false;
            }
        }
        return true;
    }

    //method that counts the total number of correct questions in question bank
    private int numCorrect(){
        int num = 0;
        for(int i = 0; i < mQuestionBank.length; i++){
            if(mQuestionBank[i].isCorrect()){
                num++;
            }
        }
        return num;
    }
}