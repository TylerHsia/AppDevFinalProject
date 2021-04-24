package com.bignerdranch.com;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;


public class QuestionFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";
    private Question mQuestion;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mTrueCheckBox;
    private CheckBox mFalseCheckBox;

    public static QuestionFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID questionID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mQuestion = QuestionBank.get(getActivity()).getQuestion(questionID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);

        mTitleField = (EditText) v.findViewById(R.id.question_title);
        //mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mQuestion.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        mTrueCheckBox = (CheckBox) v.findViewById(R.id.question_true_box);
        mTrueCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(mTrueCheckBox.isChecked()){
                    mFalseCheckBox.setChecked(false);
                }
            }
        });

        mFalseCheckBox = (CheckBox) v.findViewById(R.id.question_false_box);
        mFalseCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(mFalseCheckBox.isChecked()){
                    mTrueCheckBox.setChecked(false);
                }
            }
        });

        return v;
    }
}