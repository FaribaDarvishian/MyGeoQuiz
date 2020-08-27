package com.example.mygeoquiz.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.mygeoquiz.Controller.QuizActivity;
import com.example.mygeoquiz.Model.Question;
import com.example.mygeoquiz.QuestionRepository;
import com.example.mygeoquiz.R;

import java.util.List;

public class QuestionListFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private QuestionRepository questionRepository;
//    private Button mButtonStartGame;

    public QuestionListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionRepository = QuestionRepository.getInstance();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);
        findViews(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initUI();
        findViews(view);

        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_questions);
    }

    private void initUI() {
        List<Question> questions = questionRepository.getQuestions();
        QuestionAdapter adapter = new QuestionAdapter(questions);
        mRecyclerView.setAdapter(adapter);
    }

    private class QuestionHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewQuestion;
        private TextView mTextViewTrueAnswer;
        private CheckBox mCheckBoxIsAnswered;
        private CheckBox mCheckBoxCanCheat;
        private TextView mTextViewQuestionColor;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewQuestion = itemView.findViewById(R.id.list_row_question);
            mTextViewTrueAnswer = itemView.findViewById(R.id.list_row_true_answer);
            mCheckBoxIsAnswered = itemView.findViewById(R.id.list_row_is_answered);
            mCheckBoxCanCheat = itemView.findViewById(R.id.list_row_can_cheat);
            mTextViewQuestionColor = itemView.findViewById(R.id.list_row_text_color);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(),QuizActivity.class);
                }
            });
        }

        public void bindQuestion(Question question) {
            mTextViewQuestion.setText(question.getTextQuestion());
            mTextViewTrueAnswer.setText(question.getAnswerTrue() + "");
            if (question.getIsAnswered())
                mCheckBoxIsAnswered.setChecked(false);
            else
                mCheckBoxIsAnswered.setChecked(true);
            if (question.getIsAnswered())
                mCheckBoxCanCheat.setChecked(false);
            else
                mCheckBoxCanCheat.setChecked(true);
            mTextViewQuestionColor.setText(question.getQuestionTextColor().toString());
        }

    }

    private class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder> {
        private List<Question> mQuestions;

        public List<Question> getQuestions() {
            return mQuestions;
        }

        public void setQuestions(List<Question> questions) {
            mQuestions = questions;
        }

        public QuestionAdapter(List<Question> questions) {
            mQuestions = questions;
        }

        @NonNull
        @Override
        public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.row_list_question, parent, false);
            QuestionHolder questionHolder = new QuestionHolder(view);
            return questionHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
            Question question = mQuestions.get(position);
            holder.bindQuestion(question);
        }

        @Override
        public int getItemCount() {
            return mQuestions.size();
        }
    }

}