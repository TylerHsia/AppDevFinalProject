package com.bignerdranch.com;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class QuestionListFragment extends Fragment {

    private RecyclerView mQuestionRecyclerView;
    private QuestionAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);

        mQuestionRecyclerView = (RecyclerView) view
                .findViewById(R.id.question_recycler_view);
        mQuestionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        QuestionBank questionBank = QuestionBank.get(getActivity());
        List<Question> questions = questionBank.getQuestionBank();

        if(mAdapter == null) {
            mAdapter = new QuestionAdapter(questions);
            mQuestionRecyclerView.setAdapter(mAdapter);
        }
        else{
            //find the position
            //notify that this position was changed
            //mAdapter.notifyItemChanged(position);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class QuestionHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Question mQuestion;
        private TextView mTitleTextView;
        private TextView mTrueFalseTextView;

        public QuestionHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_question, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.question_display);
            mTrueFalseTextView = (TextView) itemView.findViewById(R.id.true_false_box);
        }

        public void bind(Question question){
            mQuestion = question;
            mTitleTextView.setText(mQuestion.getText());
            if(mQuestion.isAnswerTrue()){
                mTrueFalseTextView.setText("True");
            } else mTrueFalseTextView.setText("False");
        }

        @Override
        public void onClick(View v){

            Intent intent = QuestionActivity.newIntent(getActivity(), mQuestion.getId());
            startActivity(intent);
        }




    }

    private class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder>{

        private List<Question> mQuestions;

        public QuestionAdapter(List<Question> questions){
            mQuestions = questions;
        }

        @NonNull
        @Override
        public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new QuestionHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
            Question question = mQuestions.get(position);
            holder.bind(question);
        }

        @Override
        public int getItemCount() {
            return mQuestions.size();
        }



    }
}
