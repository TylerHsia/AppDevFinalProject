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
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;


public class QuestionFragment extends Fragment {

    private static final String ARG_QUESTION_ID = "question_id";
    private Question mQuestion;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mTrueCheckBox;
    private CheckBox mFalseCheckBox;
    private Button mDeleteButton;

    public static QuestionFragment newInstance(UUID questionID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUESTION_ID, questionID);

        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID questionID = (UUID) getArguments().getSerializable(ARG_QUESTION_ID);
        mQuestion = QuestionBank.get(getActivity()).getQuestion(questionID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);

        mTitleField = (EditText) v.findViewById(R.id.question_title);
        //if mQuestion text field != "Blank", set title field to question
        if(!mQuestion.getText().equals("Blank")) {
            mTitleField.setText(mQuestion.getText());
        }

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

        mDeleteButton = (Button) v.findViewById(R.id.delete_question_button);
        mDeleteButton.setOnClickListener(new View.OnClickListener(){
            //deletes the selected question unless there is only 1 question left
            @Override
            public void onClick(View v){
                QuestionBank questionBank = QuestionBank.get(getContext());
                if(questionBank.getLength() > 1) {
                    questionBank.getQuestionBank().remove(mQuestion);
                    getActivity().onBackPressed();
                } else{
                    Toast.makeText(getContext(), "Don't delete all the questions. Then you'll have no questions and no quiz. And you will be sad.", Toast.LENGTH_LONG).show();
                }
            }
        });


        //when true check box checked, make false checkbox unchecked and question true
        mTrueCheckBox = (CheckBox) v.findViewById(R.id.question_true_box);
        /*
        mTrueCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(mTrueCheckBox.isChecked()){
                    mFalseCheckBox.setChecked(false);
                    mQuestion.setAnswerTrue(true);
                } else{
                    mTrueCheckBox.setChecked(true); //doesn't allow both to be unchecked
                }
            }
        });
        */


        mTrueCheckBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mTrueCheckBox.isChecked()){
                    mFalseCheckBox.setChecked(false);
                    mQuestion.setAnswerTrue(true);
                } else{
                    mTrueCheckBox.setChecked(true); //doesn't allow both to be unchecked
                }
            }
        });



        //when false check box checked, make true checkbox unchecked and question false
        mFalseCheckBox = (CheckBox) v.findViewById(R.id.question_false_box);
        /*
        mFalseCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(mFalseCheckBox.isChecked()){
                    mTrueCheckBox.setChecked(false);
                    mQuestion.setAnswerTrue(false);
                } else{
                    mFalseCheckBox.setChecked(true); //doesn't allow both to be unchecked
                }
            }

        });

         */

        mFalseCheckBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mFalseCheckBox.isChecked()){
                    mTrueCheckBox.setChecked(false);
                    mQuestion.setAnswerTrue(false);
                } else{
                    mFalseCheckBox.setChecked(true); //doesn't allow both to be unchecked
                }
            }
        });

        //set initial checkbox conditions to reflect inital question conditions
        if(mQuestion.isAnswerTrue()){
            mTrueCheckBox.setChecked(true);
        } else mFalseCheckBox.setChecked(true);

        return v;
    }
}