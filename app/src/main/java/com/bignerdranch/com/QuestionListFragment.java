package com.bignerdranch.com;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;


public class QuestionListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private QuestionAdapter mAdapter;
    //variable to store UUID of the crime clicked
    private UUID UUIDPositionClicked;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
        else{
            //find the position
            int position = mAdapter.getPosition();
            //notify that this position was changed
            mAdapter.notifyItemChanged(position);
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Question mQuestion;
        private TextView mTitleTextView;
        private TextView mDateTextView;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
        }

        public void bind(Question question){
            mQuestion = question;
            mTitleTextView.setText(mQuestion.getTitle());
        }

        @Override
        public void onClick(View v){

            Intent intent = QuizActivity.newIntent(getActivity(), mQuestion.getId());
            //store the UUID
            UUIDPositionClicked = mQuestion.getId();
            startActivity(intent);
        }
    }

    private class QuestionAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Question> mQuestions;

        public QuestionAdapter(List<Question> crimes){
            mQuestions = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Question question = mQuestions.get(position);
            holder.bind(question);
        }

        @Override
        public int getItemCount() {
            return mQuestions.size();
        }


        //method to find the position of a given question in mQuestionBank
        public int getPosition(){
            int position = 0;
            for (Question question : mQuestions){
                if(question.getId().equals(UUIDPositionClicked)){
                    break;
                }
                position++;
            }
            return position;
        }
    }
}
