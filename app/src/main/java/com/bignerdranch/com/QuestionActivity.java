package com.bignerdranch.com;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class QuestionActivity extends SingleFragmentActivity {

    private static final String EXTRA_QUESTION_ID = "com.bignerdranch.android.geoquiz.question_id";

    public static Intent newIntent(Context packageContext, UUID questionID){
        Intent intent = new Intent(packageContext, QuestionActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionID);
        return intent;
    }
    @Override
    protected Fragment createFragment(){
        UUID questionID = (UUID) getIntent()
                .getSerializableExtra(EXTRA_QUESTION_ID);
        return QuestionFragment.newInstance(questionID);
    }
}
