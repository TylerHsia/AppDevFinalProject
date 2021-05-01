package com.bignerdranch.com;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();
    private Button mAddQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment).commit();
        }

        mAddQuestionButton = (Button) findViewById(R.id.add_question_button);
        mAddQuestionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //add a new question to QuestionBank and start QuestionActivity to edit that question
                List<Question> mQuestionList = QuestionBank.get(getApplicationContext()).getQuestionBank();
                mQuestionList.add(new Question("Blank", true));
                Intent intent = QuestionActivity.newIntent(getApplicationContext(), mQuestionList.get(mQuestionList.size() - 1).getId());
                startActivity(intent);
            }
        });


    }
}