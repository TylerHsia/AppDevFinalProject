package com.bignerdranch.com;

import androidx.appcompat.app.AppCompatActivity;

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
    private TextView mTextBox;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private static final String KEY_INDEX = "index";
    private Button mCheatButton;

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
        }

        mQuestionTextView = (TextView) findViewById(R.id.text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
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
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                startActivity(intent);
            }
        });


        updateQuestion();
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
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;
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