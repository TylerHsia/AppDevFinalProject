package com.bignerdranch.com;

import androidx.fragment.app.Fragment;

public class QuestionListActivity extends SingleFragmentActivity{
    protected Fragment createFragment(){
        return new QuestionListFragment();
    }

}
